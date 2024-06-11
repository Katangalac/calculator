package CalculatorModelisationTest.ConvertersTest;

import Calculator.Domain.CalculatorModelisation.Converters.UnitConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitConverterTest {

    @Test
    @DisplayName("Convert degrees to radians")
    void testDegreesToRadians() {
        UnitConverter converter = new UnitConverter();
        assertEquals(Math.PI/4, converter.degreesToRadians(45), 0.0001);
        assertEquals(Math.PI/2, converter.degreesToRadians(90), 0.0001);
        assertEquals(Math.PI, converter.degreesToRadians(180), 0.0001);
    }

    @Test
    @DisplayName("Convert radians to degrees")
    void testRadiansToDegrees() {
        UnitConverter converter = new UnitConverter();
        assertEquals(45, converter.radiansToDegrees(Math.PI / 4), 0.0001);
        assertEquals(90, converter.radiansToDegrees(Math.PI / 2), 0.0001);
        assertEquals(180, converter.radiansToDegrees(Math.PI), 0.0001);
    }

    @Test
    @DisplayName(("Convert degrees to gradians"))
    void testDegreesToGradians() {
        UnitConverter converter = new UnitConverter();
        assertEquals(50.0, converter.degreesToGradians(45), 0.0001);
        assertEquals(100.0, converter.degreesToGradians(90), 0.0001);
        assertEquals(200.0, converter.degreesToGradians(180), 0.0001);
    }

    @Test
    @DisplayName(("Convert gradians to degrees"))
    void testGradiansToDegrees() {
        UnitConverter converter = new UnitConverter();
        assertEquals(45.0, converter.gradiansToDegrees(50), 0.0001);
        assertEquals(90.0, converter.gradiansToDegrees(100), 0.0001);
        assertEquals(180.0, converter.gradiansToDegrees(200), 0.0001);
    }

    @Test
    @DisplayName(("Convert radians to gradians"))
    void testRadiansToGradians() {
        UnitConverter converter = new UnitConverter();
        assertEquals(50.0, converter.radiansToGradians(Math.PI / 4), 0.0001);
        assertEquals(100.0, converter.radiansToGradians(Math.PI / 2), 0.0001);
        assertEquals(200.0, converter.radiansToGradians(Math.PI), 0.0001);
    }

    @Test
    @DisplayName(("Convert gradiants to radians"))
    void testGradiansToRadians() {
        UnitConverter converter = new UnitConverter();
        assertEquals(Math.PI / 4, converter.gradiansToRadians(50), 0.0001);
        assertEquals(Math.PI / 2, converter.gradiansToRadians(100), 0.0001);
        assertEquals(Math.PI, converter.gradiansToRadians(200), 0.0001);
    }
}