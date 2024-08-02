package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.StringTokenizer;
import utilities.BTTree;
import exceptions.TreeException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class BSTTests {
	String filePath = "./src/test/textfile.txt";
	BTTree<String> bst = new BTTree<>();
	
	@BeforeEach
    public void setup() {
		
		
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {	         
            StringBuilder content = new StringBuilder();
            String line = "";
            System.out.println("line"+line);
            
            while ((line = br.readLine()) != null) {
                content.append(line).append(" ");
                System.out.println("line"+line);
            }

            // Separate words
            StringTokenizer tokenizer = new StringTokenizer(content.toString());
            
            while (tokenizer.hasMoreTokens()) {
                String word = tokenizer.nextToken();
                try {
                    bst.add(word);
                } catch (NullPointerException e) {
                    System.out.println("Failed to add word: " + word);
                }
            }
            
	
	} catch (IOException e) {
        e.printStackTrace();
    } 
}


	@Test
    void testTreeSize() {
        assertTrue(bst.size() > 0);
    }

    @Test
    void testContains() {
        try {
        	// assert with the first worc
            assertTrue(bst.contains("Squire"));
        } catch (TreeException e) {
            fail("Exception: " + e.getMessage());
        }
    }
        
    @Test
    void testAdd() {
        try {
            bst.add("newWord");
            assertTrue(bst.contains("newWord"));
        } catch (TreeException e) {
            fail("Exception: " + e.getMessage());
        }
    }

    @Test
    void testTreeHeight() {
        int height = bst.getHeight();
        assertTrue(height >= 0);
    }
    
    
    
}
