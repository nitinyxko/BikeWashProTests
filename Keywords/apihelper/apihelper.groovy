package apihelper

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import groovy.json.JsonBuilder
import internal.GlobalVariable

public class apihelper {

	private static Map database = [
		users   : [:],
		bookings: [:],
		services: [
			'BASIC_WASH'   : [id: 'BASIC_WASH', name: 'Basic Wash', price: 150, duration: 30],
			'PREMIUM_WASH' : [id: 'PREMIUM_WASH', name: 'Premium Wash', price: 250, duration: 45],
			'FULL_DETAILING': [id: 'FULL_DETAILING', name: 'Full Detailing', price: 500, duration: 90]
		]
	]

	@Keyword
	static String mockRegister(String phone, String name, String email, String role) {
		def userId = UUID.randomUUID().toString()
		def user = [
			id       : userId,
			phone    : phone,
			name     : name,
			email    : email,
			role     : (role ?: 'CUSTOMER'),
			createdAt: new Date().format('yyyy-MM-dd HH:mm:ss'),
			status   : 'ACTIVE'
		]
		database.users[userId] = user

		def response = [
			success: true,
			message: 'Registration successful',
			data   : [
				userId      : userId,
				sessionToken: generateToken(userId),
				user        : user
			]
		]

		return new JsonBuilder(response).toString()
	}

	@Keyword
	static String mockLogin(String phone) {
		def user = database.users.values().find { it.phone == phone }
		if (!user) {
			def errorResponse = [success: false, message: 'User not found', errorCode: 'USER_NOT_FOUND']
			return new JsonBuilder(errorResponse).toString()
		}

		def response = [
			success: true,
			message: 'OTP sent',
			data   : [userId: user.id, otpSent: true, expiresIn: 300]
		]
		return new JsonBuilder(response).toString()
	}

	@Keyword
	static String mockVerifyOTP(String userId, String otp) {
		def user = database.users[userId]
		if (!user || otp.length() != 6) {
			def errorResponse = [success: false, message: 'Invalid OTP', errorCode: 'INVALID_OTP']
			return new JsonBuilder(errorResponse).toString()
		}

		def response = [
			success: true,
			message: 'Login successful',
			data   : [
				userId      : userId,
				sessionToken: generateToken(userId),
				refreshToken: generateToken(userId + '_refresh'),
				user        : user
			]
		]
		return new JsonBuilder(response).toString()
	}

	@Keyword
	static String mockGetServices() {
		def response = [success: true, data: database.services.values() as List]
		return new JsonBuilder(response).toString()
	}

	@Keyword
	static String mockCreateBooking(String userId, Map bookingData) {
		def bookingId = UUID.randomUUID().toString()
		def service = database.services[bookingData.serviceType]
		if (!service) {
			def errorResponse = [success: false, message: 'Invalid service', errorCode: 'INVALID_SERVICE']
			return new JsonBuilder(errorResponse).toString()
		}

		def servicePrice = service.price as Integer
		def platformFee = Math.round(servicePrice * 0.10 * 100) / 100
		def totalAmount = servicePrice + platformFee

		def booking = [
			id           : bookingId,
			customerId   : userId,
			serviceType  : bookingData.serviceType,
			serviceName  : service.name,
			bikeNumber   : bookingData.bikeNumber,
			address      : bookingData.address,
			city         : bookingData.city,
			pincode      : bookingData.pincode,
			scheduledDate: bookingData.scheduledDate,
			scheduledTime: bookingData.scheduledTime,
			paymentMethod: bookingData.paymentMethod,
			servicePrice : servicePrice,
			platformFee  : platformFee,
			totalAmount  : totalAmount,
			status       : 'PENDING',
			createdAt    : new Date().format('yyyy-MM-dd HH:mm:ss')
		]
		database.bookings[bookingId] = booking

		def response = [success: true, message: 'Booking created', data: booking]
		return new JsonBuilder(response).toString()
	}

	@Keyword
	static String mockGetBooking(String bookingId) {
		def booking = database.bookings[bookingId]
		if (!booking) {
			def errorResponse = [success: false, message: 'Booking not found', errorCode: 'BOOKING_NOT_FOUND']
			return new JsonBuilder(errorResponse).toString()
		}

		def response = [success: true, data: booking]
		return new JsonBuilder(response).toString()
	}

	@Keyword
	static String mockCancelBooking(String bookingId, String reason) {
		def booking = database.bookings[bookingId]
		if (!booking) {
			def errorResponse = [success: false, message: 'Booking not found', errorCode: 'BOOKING_NOT_FOUND']
			return new JsonBuilder(errorResponse).toString()
		}

		if (booking.status in ['COMPLETED', 'CANCELLED']) {
			def errorResponse = [success: false, message: 'Cannot cancel', errorCode: 'INVALID_STATUS']
			return new JsonBuilder(errorResponse).toString()
		}

		booking.status = 'CANCELLED'
		booking.cancellationReason = reason
		booking.cancelledAt = new Date().format('yyyy-MM-dd HH:mm:ss')

		def response = [success: true, message: 'Cancelled', data: booking]
		return new JsonBuilder(response).toString()
	}

	@Keyword
	static String mockGetBookingHistory(String userId) {
		def userBookings = database.bookings.values().findAll { it.customerId == userId }
		def response = [
			success: true,
			data   : [
				bookings: userBookings,
				total   : userBookings.size()
			]
		]
		return new JsonBuilder(response).toString()
	}

	@Keyword
	static String mockAcceptBooking(String bookingId, String washerId) {
		def booking = database.bookings[bookingId]
		if (!booking) {
			def errorResponse = [success: false, message: 'Booking not found', errorCode: 'BOOKING_NOT_FOUND']
			return new JsonBuilder(errorResponse).toString()
		}

		if (booking.status != 'PENDING') {
			def errorResponse = [success: false, message: 'Booking unavailable', errorCode: 'BOOKING_UNAVAILABLE']
			return new JsonBuilder(errorResponse).toString()
		}

		booking.washerId = washerId
		booking.status = 'ASSIGNED'
		booking.assignedAt = new Date().format('yyyy-MM-dd HH:mm:ss')

		def response = [success: true, message: 'Booking accepted', data: booking]
		return new JsonBuilder(response).toString()
	}

	@Keyword
	static void resetDatabase() {
		database.users.clear()
		database.bookings.clear()
		KeywordUtil.logInfo('Mock database reset')
	}

	private static String generateToken(String seed) {
		return "TOKEN_${seed}_${System.currentTimeMillis()}"
	}
}
