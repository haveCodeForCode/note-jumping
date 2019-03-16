package com.root.cognition.test;

import com.root.cognition.common.until.ThreadsTools;

import java.util.concurrent.ExecutorService;

import static java.lang.Thread.sleep;
import java.io.*;

import java.util.ArrayList;

import java.util.List;

/**
 * @author 王睿
 * @version 2018/12/27
 */
public class test {

    public static void main(String[] args) {
        ExecutorService singleThreadPool = ThreadsTools.startThreadPool(
                1,10,ThreadsTools.buildThreadFactory("test-pool"));
        for (int i=0;i<10;i++) {
            String number = String.valueOf(i);
            singleThreadPool.execute(() -> study("u->"+number));
        }
//        singleThreadPool.shutdown();
    }

    public static void study(String value){
        try {
            int n=30;
            for (int i =0;i<n;i++){
                sleep(1000);
                System.out.println(value+"size"+i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}


/**
 * - changed BOM recognition ordering (longer boms first)
 * <p>
 * 网络地址：http://koti.mbnet.fi/akini/java/unicodereader/UnicodeReader.java.txt
 * <p>
 * Original pseudocode   : Thomas Weidenfeller
 * <p>
 * Implementation tweaked: Aki Nieminen
 * <p>
 * http://www.unicode.org/unicode/faq/utf_bom.html
 * <p>
 * BOMs:
 * <p>
 * 00 00 FE FF    = UTF-32, big-endian
 * <p>
 * FF FE 00 00    = UTF-32, little-endian
 * <p>
 * EF BB BF       = UTF-8,
 * <p>
 * FE FF          = UTF-16, big-endian
 * <p>
 * FF FE          = UTF-16, little-endian
 * <p>
 * Win2k Notepad:
 * <p>
 * Unicode format = UTF-16LE
 ***/

/**
 * Generic unicode textreader, which will use BOM mark
 * to identify the encoding to be used. If BOM is not found
 * then use a given default or system encoding.

 */

class UTF8BOMConverter extends Reader {

    PushbackInputStream internalIn;
    InputStreamReader internalIn2 = null;
    String defaultEnc;
    private static final int BOM_SIZE = 4;

    /**
     * @param in         inputstream to be read
     * @param defaultEnc default encoding if stream does not have
     *                   BOM marker. Give NULL to use system-level default.
     */

    UTF8BOMConverter(InputStream in, String defaultEnc) {
        internalIn = new PushbackInputStream(in, BOM_SIZE);
        this.defaultEnc = defaultEnc;
    }


    public String getDefaultEncoding() {
        return defaultEnc;
    }

    /**
     * Get stream encoding or NULL if stream is uninitialized.
     * Call init() or read() method to initialize it.
     * @return 编码
     */
    public String getEncoding() {
        if (internalIn2 == null) {
            return null;
        }
        return internalIn2.getEncoding();
    }


    /**
     * Read-ahead four bytes and check for BOM marks. Extra bytes are
     * unread back to the stream, only BOM bytes are skipped.
     */

    protected void init() throws IOException {
        if (internalIn2 != null) {
            return;
        }
        String encoding;
        byte bom[] = new byte[BOM_SIZE];
        int n, unread;
        n = internalIn.read(bom, 0, bom.length);
        if ((bom[0] == (byte) 0x00) && (bom[1] == (byte) 0x00) &&
                (bom[2] == (byte) 0xFE) && (bom[3] == (byte) 0xFF)) {
            encoding = "UTF-32BE";
            unread = n - 4;
        } else if ((bom[0] == (byte) 0xFF) && (bom[1] == (byte) 0xFE) &&
                (bom[2] == (byte) 0x00) && (bom[3] == (byte) 0x00)) {
            encoding = "UTF-32LE";
            unread = n - 4;
        } else if ((bom[0] == (byte) 0xEF) && (bom[1] == (byte) 0xBB) &&
            (bom[2] == (byte) 0xBF)) {
            encoding = "UTF-8";
            unread = n - 3;
        } else if ((bom[0] == (byte) 0xFE) && (bom[1] == (byte) 0xFF)) {
            encoding = "UTF-16BE";
            unread = n - 2;
        } else if ((bom[0] == (byte) 0xFF) && (bom[1] == (byte) 0xFE)) {
            encoding = "UTF-16LE";
            unread = n - 2;
        } else {
            // Unicode BOM mark not found, unread all bytes
            encoding = defaultEnc;
            unread = n;

        }

        //System.out.println("read=" + n + ", unread=" + unread);

        if (unread > 0) internalIn.unread(bom, (n - unread), unread);

        // Use given encoding

        if (encoding == null) {

            internalIn2 = new InputStreamReader(internalIn);

        } else {

            internalIn2 = new InputStreamReader(internalIn, encoding);

        }

    }

    @Override
    public void close() throws IOException {
        init();
        internalIn2.close();

    }


    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {

        init();

        return internalIn2.read(cbuf, off, len);

    }


    private static void readContentAndSaveWithEncoding(String filePath, String readEncoding, String saveEncoding) throws Exception {
        saveContent(filePath, readContent(filePath, readEncoding), saveEncoding);
    }

    private static void saveContent(String filePath, String content, String encoding) throws Exception {
        FileOutputStream fos = new FileOutputStream(filePath);
        OutputStreamWriter w = new OutputStreamWriter(fos, encoding);
        w.write(content);
        w.flush();
    }


    private static String readContent(String filePath, String encoding) throws Exception {
        FileInputStream file = new FileInputStream(new File(filePath));
        BufferedReader br = new BufferedReader(new UTF8BOMConverter(file, encoding));
        String line = null;
        String fileContent = "";
        while ((line = br.readLine()) != null) {
            fileContent = fileContent + line;
            fileContent += "\r\n";
        }
        return fileContent;
    }

    private static List<String> getPerlineFileName(String filePath) throws Exception {
        FileInputStream file = new FileInputStream(new File(filePath));
        BufferedReader br = new BufferedReader(new InputStreamReader(file, "UTF-8"));
        String line = null;
        List<String> list = new ArrayList<String>();
        while ((line = br.readLine()) != null) {
            list.add(line);
        }
        return list;
    }


    private static List<String> getAllFilePaths(File filePath, List<String> filePaths) {
        File[] files = filePath.listFiles();
        if (files == null) {
            return filePaths;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                filePaths.add(f.getPath());
                getAllFilePaths(f, filePaths);
            } else {
                filePaths.add(f.getPath());
            }
        }
        return filePaths;
    }

    public static void main(String[] args) throws Exception {
        String suffix = ".java";
        List<String> paths = new ArrayList<String>();
        paths = getAllFilePaths(new File("E:/javaCodeBase/backups/bootdo/bootdo"), paths);
        List<String> pathList = new ArrayList<String>();
        for (String path : paths) {
            if (path.endsWith(suffix)) {
                pathList.add(path);
            }
        }
        for (String path : pathList) {
            readContentAndSaveWithEncoding(path, "UTF-8", "UTF-8");
            System.out.println(path + "转换成功");
        }

    }

}




    /**
     * 验证Map声明使用的方法
        List value1 =new ArrayList();
        List value2 =new ArrayList();
        Map<String,String> gh=new HashMap<>();
        int n = 10;
        int i = bootstrap;
            while (i<n){
            Map<String,String> wh = new HashMap<>();
            String n1 = n+"";
            String i1 = i+"";
            gh.put(i1,n1);
            wh.put(i1,n1);
            value1.add(gh);
            value2.add(wh);
            n=n-1;
            i++;
        }
            System.out.println(value1);
            System.err.println(value2);
     */
