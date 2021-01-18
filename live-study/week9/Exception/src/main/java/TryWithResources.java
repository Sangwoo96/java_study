import java.io.*;

public class TryWithResources{
    static void copy(String src, String dest) throws IOException {
        InputStream in = null;
        OutputStream out = null;
        try{
            in = new FileInputStream(src);
            out = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int n;
            while((n = in.read(buf)) >= 0)
                out.write(buf, 0 ,n);

        }finally{
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
