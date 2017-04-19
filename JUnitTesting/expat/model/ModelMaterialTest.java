package expat.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * is responsible for
 * <p>
 * created on 06.04.2017
 *
 * @author vanonir
 */
public class ModelMaterialTest {
    /**
     * Test if correct amount from another material will be reduced.
     *
     * @throws Exception
     */
    @Test
    public void addMaterialTest() throws Exception {
        ModelMaterial mat = new ModelMaterial();
        assertArrayEquals(new int[]{0,0,0,0,0},mat.getMaterialAmount());
        mat.addMaterial(new ModelMaterial(new int[]{1,1,1,1,1}));
        assertArrayEquals(new int[]{1,1,1,1,1},mat.getMaterialAmount());
    }

    /**
     * Checks if correct amount are reduced. and methode returns false if it would go below zero.
     *
     * @throws Exception
     */
    @Test
    public void reduceMaterialTest() throws Exception {
        ModelMaterial mat = new ModelMaterial(new int[]{1,1,1,1,1});
        assertArrayEquals(new int[]{1,1,1,1,1},mat.getMaterialAmount());
        mat.reduceMaterial(new ModelMaterial(new int[]{1,1,1,1,1}));
        assertArrayEquals(new int[]{0,0,0,0,0},mat.getMaterialAmount());
        assertFalse(mat.reduceMaterial(new ModelMaterial(new int[]{1,1,1,1,1})));

    }

    /**
     * Tests string output of methode containing all amounts and material names.
     *
     * @throws Exception
     */
    @Test
    public void allMaterialsStringTest() throws Exception {
        ModelMaterial mat = new ModelMaterial(new int[]{1,0,0,0,0});
        assertEquals("1 Clay\n" +
                "0 Grain\n" +
                "0 Stone\n" +
                "0 Wood\n" +
                "0 Wool\n",mat.allMaterialsString());
    }

    /**
     * Tests if amount of all materials summed is correct.
     *
     * @throws Exception
     */
    @Test
    public void getSumOfAllMaterialsTest() throws Exception {
        ModelMaterial mat = new ModelMaterial(new int[]{0,1,2,3,4});
        assertEquals(10,mat.getSumOfAllMaterials());
    }

    @Test
    public void takeArandomRessource() throws Exception{
        ModelMaterial mat = new ModelMaterial(new int[]{0,0,1,0,0});
        assertEquals(1,mat.getSumOfAllMaterials());
        ModelMaterial matTaken = mat.takeRandomMaterial();
        assertEquals(0,mat.getSumOfAllMaterials());
        assertEquals(1,matTaken.getSumOfAllMaterials());
    }
}