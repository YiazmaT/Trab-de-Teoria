
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/*
 * Assembler para a maquina de multi-fitas criada como trabalho para simular um
 * computador
 * Aceita programas como:
 * a = 10
 * b = 5
 * Load a
 * Swap
 * Load b
 * Add
 * Store a
 *
 * Incorretamente aceita instruções como: "Load Add Sub asdoaisdoi". Interpretando
 * como Add devido a pressa e a forma como foi implementado
 * 
 */
/**
 *
 * @author Matheus Prachedes Batista
 */
public class CompiladorTuring {

    private static final int ADD = 0;
    private static final int SUB = 1;
    private static final int AND = 2;
    private static final int NOT = 3;
    private static final int SWAP = 4;
    private static final int LOAD = 5;
    private static final int STORE = 6;
    private static final int JUMP = 7;
    private static final int VAR = 8;

    private static final String ADDOP = "000";
    private static final String SUBOP = "001";
    private static final String ANDOP = "010";
    private static final String NOTOP = "011";
    private static final String SWAPOP = "100";
    private static final String LOADOP = "101";
    private static final String STOREOP = "110";
    private static final String JUMPOP = "111";

    private ArrayList<String> fonte;
    private ArrayList<String> memoria;
    private HashMap<String, Integer> variaveis;
    private Queue<String> loadStoreOperations;
    
    public CompiladorTuring(File arquivo) {
        fonte = new ArrayList<>();
        memoria = new ArrayList<>();
        variaveis = new HashMap<>();
        loadStoreOperations = new LinkedList<String>();
        lerArquivo(arquivo);
    }

    public String assembly() {
        for (int i =0;i<fonte.size();i++) {
            String s = fonte.get(i);
            int op = getOp(s);
            switch (op) {
                case ADD:
                    memoria.add(ADDOP);
                    break;
                case SUB:
                    memoria.add(SUBOP);
                    break;
                case AND:
                    memoria.add(ANDOP);
                    break;
                case NOT:
                    memoria.add(NOTOP);
                    break;
                case SWAP:
                    memoria.add(SWAPOP);
                    break;
                case LOAD:
                    memoria.add(LOADOP);
                    loadStoreOperations.add(getLoadStoreVar(s));
                    break;
                case STORE:
                    memoria.add(STOREOP);
                    loadStoreOperations.add(getLoadStoreVar(s));
                    break;
                case JUMP:
                    int jumpAddress = getJumpValue(s);
                    memoria.add(JUMPOP + toBinary(jumpAddress));
                    break;
                case VAR:
                    addVar(s);
                    break;
            }
        }
        ArrayList<String> chaves = new ArrayList<>(variaveis.keySet());
        ArrayList<Integer> valores = new ArrayList<>(variaveis.values());
        String saida = "";
        for(int i=0;i<memoria.size();i++){
            String s = memoria.get(i);
            if(s.equals(LOADOP) || s.equals(STOREOP)){
                String var = loadStoreOperations.poll();
                int index = chaves.indexOf(var);
                s = s+toBinary(memoria.size()+2+index);
            }
            saida=saida+toBinary(i)+"*"+s+"#";
        }
        for(int i=0;i<chaves.size();i++){
            saida= saida+toBinary(memoria.size()+2+i)+"*"+toBinary(valores.get(i))+"#";
        }
        saida = saida.substring(0, saida.lastIndexOf("#"));
        
        return saida;
    }

    private int getOp(String s) {
        if (s.toUpperCase().contains("ADD")) {
            return ADD;
        }
        if (s.toUpperCase().contains("SUB")) {
            return SUB;
        }
        if (s.toUpperCase().contains("AND")) {
            return AND;
        }
        if (s.toUpperCase().contains("NOT")) {
            return NOT;
        }
        if (s.toUpperCase().contains("SWAP")) {
            return SWAP;
        }
        if (s.toUpperCase().contains("LOAD")) {
            return LOAD;
        }
        if (s.toUpperCase().contains("STORE")) {
            return STORE;
        }
        if (s.toUpperCase().contains("JUMP")) {
            return JUMP;
        }
        if (s.toUpperCase().contains("=")) {
            return VAR;
        }
        return -1;
    }

    private void lerArquivo(File arquivo) {
        if (arquivo.exists()) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo)));
                String r = br.readLine();
                while (r != null) {
                    fonte.add(r);
                    r = br.readLine();
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CompiladorTuring.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(CompiladorTuring.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void addVar(String s) {
        String[] linha = s.split(" ");
        String varName = linha[0];
        Integer valor = Integer.valueOf(linha[2]);
        variaveis.put(varName, valor);
    }

    private String getLoadStoreVar(String s) {
        String[] split = s.split(" ");
        String varName = split[1];
        return varName;
    }
    
    private int getJumpValue(String s) {
        String[] split = s.split(" ");
        return Integer.valueOf(split[1]);
    }
    
    public static void main(String[] args) {
        JFileChooser jf = new JFileChooser();
        int op = jf.showOpenDialog(null);
        if (op == JFileChooser.APPROVE_OPTION) {
            System.out.println(new CompiladorTuring(jf.getSelectedFile()).assembly());
        }

    }

    private String toBinary(int jumpAddress) {
        return Integer.toBinaryString(jumpAddress);
    }

    

    

}
