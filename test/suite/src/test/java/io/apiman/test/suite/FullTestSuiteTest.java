/*
 * Copyright 2015 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.apiman.test.suite;

import io.apiman.manager.test.junit.RestTestPlan;
import io.apiman.manager.test.junit.RestTestSystemProperties;
import io.apiman.manager.test.junit.RestTester;

import org.junit.runner.RunWith;

/**
 * Runs the {@link FullTestSuite} as a junit test.  The {@link FullTestSuite} class
 * is a smoke test usually targetted at a fresh installation of apiman.
 *
 * @author eric.wittmann@redhat.com
 */
@RunWith(RestTester.class)
@RestTestSystemProperties({
    "apiman.junit.no-server", "true",
    "apiman.junit.server-port", "8080",
    "apiman.suite.api-username", "admin",
    "apiman.suite.api-password", "admin123!",
    "apiman.suite.gateway-config-endpoint", "http://localhost:8080/apiman-gateway-api",
    "apiman.suite.gateway-config-username", "admin",
    "apiman.suite.gateway-config-password", "admin123!",
    "apiman.suite.echo-endpoint", "http://localhost:8080/services/echo"
})
public class FullTestSuiteTest {

    @RestTestPlan(value="scripts/api-manager-testPlan.xml", order = 1)
    public void managerTests() {}

    @RestTestPlan(value="scripts/api-gateway-testPlan.xml", order = 2, endpoint="${apiman.full-test-suite.gateway-endpoint : http://localhost:8080/apiman-gateway}")
    public void gatewayTests() {}

}
