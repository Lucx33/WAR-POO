package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Load {




    public void Load() throws IOException {
        Scanner s = null;
        try {
            s = new Scanner(new BufferedReader(new FileReader("save.txt")));
            while (s.hasNext())
                System.out.println(s.next());
        } finally {
            if (s != null)
                s.close();
        }
    }
}
