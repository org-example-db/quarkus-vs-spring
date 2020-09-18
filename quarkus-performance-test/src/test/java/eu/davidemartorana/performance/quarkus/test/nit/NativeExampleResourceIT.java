package eu.davidemartorana.performance.quarkus.test.nit;

import io.quarkus.test.junit.NativeImageTest;

import eu.davidemartorana.performance.quarkus.test.bdt.RandomControllerAPITest;

@NativeImageTest
public class NativeExampleResourceIT extends RandomControllerAPITest {

    // Execute the same tests but in native mode.
}
