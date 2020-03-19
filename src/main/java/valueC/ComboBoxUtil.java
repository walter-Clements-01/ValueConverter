package valueC;

import valueC.ComboBoxes;

import java.io.*;

public class ComboBoxUtil
{
    public static ComboBoxes load()
    {
        ComboBoxes comboBoxes;
        try
        {
            FileInputStream fileIn = new FileInputStream("boxes.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            comboBoxes = (ComboBoxes) in.readObject();
            in.close();
            fileIn.close();
        }
        catch (IOException i)
        {
            comboBoxes= new ComboBoxes();
            i.printStackTrace();
        } catch (ClassNotFoundException c)
        {
            comboBoxes= new ComboBoxes();
            System.out.println("Employee class not found");
            c.printStackTrace();
        }
        return comboBoxes;
    }
    public static void save(ComboBoxes comboBoxes)
    {
        try
        {
            FileOutputStream fileOut = new FileOutputStream("boxes.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(comboBoxes);
            out.close();
            fileOut.close();
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }
    }
}
