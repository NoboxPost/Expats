package expat.model.board;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * is responsible for
 * <p>
 * created on 06.04.2017
 *
 * @author vanonir
 */
public class ModelHexTest {


    /**
     * Tests if a new dice number can be assigned if hex has a dice number of 0;
     *
     * @throws Exception
     */
    @Test
    public void assignDiceNumberTest() throws Exception {
        ModelHex hex = new ModelHex(0,0);
        assertEquals(0,hex.getDiceNumber());
        hex.assignDiceNumber(8);
        assertEquals(8,hex.getDiceNumber());
        hex.assignDiceNumber(5);
        assertEquals(8,hex.getDiceNumber());

    }

    /**
     * Test if a hex radided boolean is false by default, and can by changed by "raid" method.
     *
     * @throws Exception
     */
    @Test
    public void raidTest() throws Exception {
        ModelHex hex = new ModelHex(0,0);
        assertEquals(false,hex.getRaided());
        hex.raid();
        assertEquals(true,hex.getRaided());
    }

    /**
     * Assigns a new type and a dice number. A corresponding ModelMaterial will be made.
     *
     * @throws Exception
     */
    @Test
    public void assignTypeAndDiceNumberTest() throws Exception {
        ModelHex hex = new ModelHex(0,0);
        assertEquals("Water",hex.getType());
        hex.assignTypeAndDiceNumber("Clay",8);
        assertEquals("Clay",hex.getType());
        assertArrayEquals(new int[]{1,0,0,0,0},hex.getMaterial().getMaterialAmount());
    }

    /**
     * checks if x coord and y coord are returned in an int array.
     *
     * @throws Exception
     */
    @Test
    public void getCoordsTest() throws Exception {
        ModelHex hex = new ModelHex(5,9);
        assertArrayEquals(new int[]{5,9},hex.getCoords());
    }


}