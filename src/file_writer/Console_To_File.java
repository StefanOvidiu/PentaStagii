package file_writer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Console_To_File {
	private Path path;
	private String content;
	
	Console_To_File(){
		path=Paths.get("test.txt");
	}
	
	Console_To_File(String pathS){
		this.path=Paths.get(pathS);
	}
	
	Console_To_File(String pathS,String content){
		this.path=Paths.get(pathS);
		this.content=content;
	}
	
	public void WriteToFile() {
		
		//scriu in fisier cu ajutorul argumentului constructorului
		if(content==null) {
			int optiune=0;
			System.out.println("0-Clear the file \n1-Continue with content from existing file");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			try {
				optiune=Integer.parseInt(in.readLine());
			} catch (NumberFormatException e1) {
				System.out.println("Input eronat ! Input-ul trebuie sa fie numar !");
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(optiune==0){
				try (BufferedWriter writer = Files.newBufferedWriter(path,StandardCharsets.UTF_8)){
				while((content=in.readLine()).length()!=0) {
					writer.write(content);
					writer.newLine();
					writer.flush();
				}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(optiune==1) {
				//verificare daca fisierul exista
				File f=new File(path.toString());
				if(f.exists()) {
					try (BufferedWriter writer = Files.newBufferedWriter(path,StandardCharsets.UTF_8, StandardOpenOption.APPEND)){
						while((content=in.readLine()).length()!=0) {
							writer.write(content);
							writer.newLine();
							writer.flush();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					System.out.println("Nu se poate face scrierea in continuare fiindca fisierul este inexistent ! ");
					System.exit(0);
				}
			}
			else {
				System.out.println("Optiune gresita");
				System.exit(0);
			}
		}
		else{
			try (BufferedWriter writer = Files.newBufferedWriter(path,StandardCharsets.UTF_8)){
					writer.write(content);
					writer.newLine();
					writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
