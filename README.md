# BikeWash Pro Test Automation Documentation

## Overview
This document provides comprehensive documentation for the BikeWash Pro mobile application test automation framework.

## Test Structure

### 1. Authentication Tests
- **TC01_CustomerRegistration**: Customer registration flow
- **TC02_WasherRegistration**: Washer registration flow
- **TC03_Login**: Login functionality for both customers and washers

### 2. Customer Flow Tests
- **TC05_BookBasicWash**: Basic wash booking process
- **TC06_BookPremiumWash**: Premium wash booking process
- **TC08_TrackOrder**: Order tracking functionality

### 3. API Tests
- **TC_API_Auth**: Authentication API tests
- **TC_API_Order**: Order management API tests

## Test Configuration

### Environment Setup
1. Configure environment in Profile settings:
   - Dev: api-dev.bikewashpro.com
   - QA: api-qa.bikewashpro.com
   - Staging: api-staging.bikewashpro.com

### Test Data
- Test phone numbers configured per environment
- Mock data for registration and orders
- Configuration in GlobalVariable.groovy

## Running Tests

### Test Suites
1. **TS_Smoke**: Quick validation of critical paths
   - Basic authentication
   - Core functionality

2. **TS_Authentication**: Complete auth test coverage
   - Customer registration
   - Washer registration
   - Login flows

3. **TS_CustomerFlow**: End-to-end customer scenarios
   - Booking process
   - Order tracking

4. **TS_Regression**: Full regression suite
   - All test cases
   - Complete coverage

### Execution Instructions
1. Select desired test suite
2. Choose execution profile (dev/qa/staging)
3. Configure mobile device/emulator
4. Run the suite

## Reporting

### Test Reports Location
- Reports stored in Reports/ directory
- Screenshots in Screenshots/ directory

### Report Types
1. HTML Reports
   - Detailed test execution results
   - Screenshots for failed tests
   - Execution logs

2. JUnit Reports
   - CI/CD integration
   - Test metrics

3. PDF Reports
   - Executive summary
   - Test coverage metrics

## Maintenance

### Adding New Tests
1. Create test case in appropriate folder
2. Add to relevant test suite
3. Update documentation
4. Create test data if needed

### Updating Test Objects
1. Update object repository
2. Verify in Object Spy
3. Test changes in isolation

## Best Practices

### Mobile Testing
1. Always clear app data before tests
2. Handle device permissions
3. Manage keyboard visibility
4. Use appropriate waits

### API Testing
1. Maintain token management
2. Validate response structure
3. Handle timeouts appropriately
4. Clean up test data