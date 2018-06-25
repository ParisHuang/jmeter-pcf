Feature: Performance test for pcf safearrival api
  as a lots of users
  we want to get safearrival api
     
  @JmeterPcfTest
  Scenario: many guardian instantaneous concurrent call get safearrival api
    Given ${users} threads is loaded
      And ramp-up period is set to ${rampupPeriod} seconds
      And only run one time for each threads
      And ${users} guardian login system
      And call safearrival api:"get SafeArrival API"
     When call jmeter with safearrival api jmx script:"src/test/resources/jmx/GetSafeArrivalAPI.jmx"
     Then verify the response time for all call api
     