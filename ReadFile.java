import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class ReadFile
{
  private String path;
  
  public ReadFile(String file_path)
  {
    path = file_path;
  }
  
  public Sting[] OpenFile() throws IOException {
    
    FileReader fr = new FileReader(path);
    BufferedReader textReader = new BufferedReader(fr);
    
    int numberOfLines = 3;
    String[] textData = new String[numberOfLines];
    
    for (int i = 0; i < numberOfLines; i++)
    {
      textData[i] = textReader.readLine();
    }
    
  }
  