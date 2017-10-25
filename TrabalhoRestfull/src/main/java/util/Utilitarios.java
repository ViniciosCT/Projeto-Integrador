package util;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 * Created by v_c_t on 06/10/2017.
 */
public class Utilitarios {

    private static final String DIR = "C:/Users/v_c_t/Dropbox/Aulas/5_Semestre/Rec_e_Person_na_web/TrabalhoRestfull/src/main/webapp";


    //Indexação:
    public void indexacao(String textoArtigo) throws IOException{

        Random random = new Random();
        double x = random.nextDouble();

        String nome = textoArtigo.split(" ")[0] + x;
        String docOriginal = this.DIR + "/docOriginais/" + nome+ ".txt";
        String docLucine = this.DIR + "/docLucine/";

        this.guardaTexto(textoArtigo, docOriginal);
        this.guardaLucine(textoArtigo, docLucine, docOriginal);

    }

    private void guardaTexto(String textoArtigo, String docOriginal) throws IOException {

        File arquivo = new File(docOriginal);

        BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo),"UTF-8"));
        fw.write(textoArtigo);
        fw.flush();
        fw.close();
    }

    private void guardaLucine(String textoArtigo, String docLucine, String docOriginal) throws IOException {
        IndexWriter writer = createWriter(docLucine);
        List<Document> documents = new ArrayList<Document>();

        Document document = createDocument(textoArtigo, docOriginal);
        documents.add(document);

        writer.addDocuments(documents);
        writer.commit();
        writer.close();
    }

    private static Document createDocument(String textoArtigo, String docOriginal) {
        Document document = new Document();

        document.add(new TextField("textoArtigo", textoArtigo , Field.Store.YES));
        document.add(new StringField("dirDocOriginal", docOriginal , Field.Store.YES));

        return document;
    }

    private static IndexWriter createWriter(String docLucine) throws IOException {

        FSDirectory dir = FSDirectory.open(Paths.get(docLucine));
        IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
        IndexWriter writer = new IndexWriter(dir, config);

        return writer;
    }


    //Consulta:
    public ArrayList<String> recuperaArtigo(String consulta) throws Exception {

        String docLucine = this.DIR + "/docLucine/";
        ArrayList<String> saidas;

        saidas = consultaLucene(consulta, docLucine);

        return saidas;
    }

    private ArrayList consultaLucene(String consulta, String docLucine) throws Exception{

        IndexSearcher searcher = createSearcher(docLucine);
        TopDocs foundDocs = searchByContents(consulta, searcher);

        ArrayList<String> respost = new ArrayList<String>();

        for (ScoreDoc sd : foundDocs.scoreDocs) {

            Document d = searcher.doc(sd.doc);

            respost.add(String.format(d.get("dirDocOriginal")));

        }

        return respost;
    }


    private static TopDocs searchByContents(String consulta, IndexSearcher searcher) throws Exception {

        QueryParser qp = new QueryParser("textoArtigo", new StandardAnalyzer());
        Query contentsQuery = qp.parse(consulta);
        TopDocs hits = searcher.search(contentsQuery, 10);

        return hits;
    }

    private static IndexSearcher createSearcher(String docLucine) throws IOException {

        Directory dir = FSDirectory.open(Paths.get(docLucine));
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);

        return searcher;
    }

    public static void main(String[] args){

        //String artigo="Ola aqui é o Vitor, como você esta?";
        String consulta="Vitor";

        Utilitarios ut = new Utilitarios();

        try {

            //ut.indexacao(artigo);
            System.out.println(ut.recuperaArtigo(consulta));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}