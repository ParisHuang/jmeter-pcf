$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("features/api/Get Absence List.feature");
formatter.feature({
  "line": 1,
  "name": "Performance test for safearrival api",
  "description": "as a lots of guardian\r\nwe want to get absence list",
  "id": "performance-test-for-safearrival-api",
  "keyword": "Feature"
});
formatter.before({
  "duration": 2158575,
  "status": "passed"
});
formatter.scenario({
  "line": 6,
  "name": "5 guardian call get absence list api",
  "description": "",
  "id": "performance-test-for-safearrival-api;5-guardian-call-get-absence-list-api",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 5,
      "name": "@test"
    }
  ]
});
formatter.step({
  "line": 7,
  "name": "5 threads is loaded",
  "keyword": "Given "
});
formatter.step({
  "line": 8,
  "name": "ramp-up period is set to 5 second",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "duration run time is set to 20 second",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "5 guardian login system",
  "keyword": "And "
});
formatter.step({
  "line": 11,
  "name": "call safearrival api:\"get absence list\"",
  "keyword": "And "
});
formatter.step({
  "line": 12,
  "name": "call jmeter with jmx script:\"src/test/resources/jmx/api/GetAbsenceList.jmx\"",
  "keyword": "When "
});
formatter.step({
  "line": 13,
  "name": "verify the response time for all call api",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "5 threads is loaded",
      "offset": 0
    },
    {
      "val": "5",
      "offset": 0
    }
  ],
  "location": "CustomSteps.givenAllThreadsIsLoaded(String,int)"
});
formatter.result({
  "duration": 65997881,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "ramp-up period is set to 5 second",
      "offset": 0
    },
    {
      "val": "5",
      "offset": 25
    }
  ],
  "location": "CustomSteps.rampupPeriodIsSeted(String,int)"
});
formatter.result({
  "duration": 96451,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "duration run time is set to 20 second",
      "offset": 0
    },
    {
      "val": "20",
      "offset": 28
    }
  ],
  "location": "CustomSteps.durationTimeIsSeted(String,int)"
});
formatter.result({
  "duration": 73916,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "5 guardian login system",
      "offset": 0
    },
    {
      "val": "5",
      "offset": 0
    }
  ],
  "location": "CustomSteps.callGetUserOptions(String,int)"
});
formatter.result({
  "duration": 67305,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "call safearrival api:\"get absence list\"",
      "offset": 0
    },
    {
      "val": "get absence list",
      "offset": 22
    }
  ],
  "location": "CustomSteps.callCommunicateApi(String,String)"
});
formatter.result({
  "duration": 59794,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "call jmeter with jmx script:\"src/test/resources/jmx/api/GetAbsenceList.jmx\"",
      "offset": 0
    },
    {
      "val": "src/test/resources/jmx/api/GetAbsenceList.jmx",
      "offset": 29
    }
  ],
  "location": "CustomSteps.callJmeterWithJmxFile(String,String)"
});
formatter.result({
  "duration": 25457639473,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "verify the response time for all call api",
      "offset": 0
    }
  ],
  "location": "CustomSteps.verifyResponseTime(String)"
});
formatter.result({
  "duration": 5343854,
  "status": "passed"
});
formatter.after({
  "duration": 5791656977,
  "status": "passed"
});
});