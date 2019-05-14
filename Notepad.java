import java.awt.*;
import java.awt.event.*;
import java.io.*;
class MyNotepade extends Frame implements ActionListener
{
    private TextArea text;
    private Menu file;
    private MenuItem New,Open,Save,Exit;
    private MenuBar mb=new MenuBar();
    
    public MyNotepade()
    {
        file=new Menu("File");
        New=new MenuItem("New");
        Open=new MenuItem("Open");
        Save=new MenuItem("Save");
        Exit=new MenuItem("Exit");
        
        file.add(New);
        file.add(Open);
        file.add(Save);
        file.add(Exit);

        
        mb.add(file);
        
        New.addActionListener(this);
        Open.addActionListener(this);
        Save.addActionListener(this);
        Exit.addActionListener(this);

        
        setTitle("Deep-Jemish Notpad");
        setSize(600,600);
        setLocation(300,300);
        setMenuBar(mb);
        
        text=new TextArea();
        add(text);
        
      addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent we)
            {
                System.exit(0);
            }
        });
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==New)

        {
            text.setText(" ");
        }
        else if(ae.getSource()==Open)
        {
            try
            {
                FileDialog fd=new FileDialog(this,"Open File",FileDialog.LOAD);
                fd.setVisible(true);
                String dir=fd.getDirectory();
                String fname=fd.getFile();
                FileInputStream fis=new FileInputStream(dir+fname);
                DataInputStream dis=new DataInputStream(fis);
                String str=" ",msg=" ";
                while((str=dis.readLine())!=null)
                {
                    msg=msg+str;
                    msg+="\n";
                }
                text.setText(msg);
                dis.close();
            }
            catch(Exception e){}
        }
        else if(ae.getSource()==Save)
        {
            try
            {
                FileDialog fr=new FileDialog(this,"Save File",FileDialog.SAVE);
                fr.setVisible(true);
                String txt=text.getText();
                String dir=fr.getDirectory();
                String fname=fr.getFile();
                FileOutputStream fos=new FileOutputStream(dir+fname);
                DataOutputStream dos=new DataOutputStream(fos);
                dos.writeBytes(txt);
                dos.close();
            }
            catch(Exception e)
            {
            }
        }
        else if(ae.getSource()==Exit)
        {
            System.exit(0);
        }
    }
}
class Notepad
{
    public static void main(String []args)
    {
        new MyNotepade().setVisible(true);
    }
}