import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class CadernosDePalavras {
    public static String[] D0101 = {
        "SOFÁ", "BONÉ", "AQUARELA", "NOVELA", "RUA", "GELO", "AMIGO", "PETECA", "MIL", "DINOSSAURO", "CABIDE", "EXEMPLO", "SECA", "BARRIGA", "CUBO", "BATUCADA", "LEI", "NINHO", "FAIXA", "COLMEIA", "TACO", "HIPOPÓTAMO", "TAXA", "SOL", "FOLIA", "JOGADA", "VÉU", "FADA", "PIQUE", "SAPATO", "TOMATE", "FIVELA", "TELEFONE", "PLUMA", "OURO", "POSE", "MARINHEIRO", "REDE", "PIRULITO", "FIXO", "CARAMELO", "TREM", "VICE", "ÚLTIMO", "PLATEIA", "ABELHA", "MARIDO", "PILHA", "CAMINHÃO", "XÍCARA"
    };
    public static String[] D0102 = {
        "SOFÁ", "BIBLIOTECA", "IGUAL", "DOMINÓ", "BOI", "CHAVE", "ALMANAQUE", "GALO", "TELHADO", "PEQUENO", "COR", "BECO", "ABELHA", "FAROFA", "CORUJA", "JARARACA", "NAVE", "GELO", "POUCO", "CARAVELA", "REINO", "GORILA", "LÃ", "HOJE", "ALVO", "BANANA", "FITA", "COGUMELO", "PANELA", "GUITARRA", "MEL", "SALADA", "LÂMPADA", "COLO", "ÚNICO", "FILHO", "LUZ", "GALINHEIRO", "POEMA", "VELA", "SONHO", "COMETA", "SEIS", "TÁXI", "PISCINA", "NATURAL", "BERRO", "CAMINHÃO", "TAXA", "PIRULITO"
    };
    public static String[] D0103 = {
        "GIRAFA", "VOZ", "OUVIDO", "MANHÃ", "BIGODE", "AVENIDA", "CÉU", "MARRECO", "BICO", "FOME", "MÃE", "BOLADA", "LONA", "FAROL", "ESPANTALHO", "COLEIRA", "ESPADA", "DELEGADO", "FERRÃO", "RIMA", "MOLA", "GAFANHOTO", "QUEIJO", "VELHO", "NEVE", "ROUXINOL", "DÍVIDA", "PAU", "ÔNIBUS", "FILHOTE", "TOMADA", "VIOLÃO", "SANDUÍCHE", "MEIO", "PÉ", "FIGURA", "MATEMÁTICA", "CABELO", "PORTA", "SUCO", "BANHEIRO", "REI", "FOGUEIRA", "SESSENTA", "UVA", "SABONETE", "TOUCA", "RIO", "PALITO", "MOÇO"};
    public static String[] D0301 = {
        "ANEL", "MUSA", "GINCANA", "PIQUE", "IRMÃO", "GUARANÁ", "SOFÁ", "LÍNGUA", "CEBOLA", "CAMINHÃO", "VIOLÃO", "ZAGUEIRO", "REDE", "NOVELA", "ONÇA", "ÉGUA", "BABOSA", "NOVO", "GATO", "DUPLA", "NÓ", "TIJOLO", "MANCHA", "DITADO", "MELANCIA", "RUA", "NINHO", "AVENIDA", "ZEBRA", "POSE", "QUEIJO", "FOGUETE", "CINTO", "GELO", "POEMA", "PACOTE", "TABULEIRO", "AMIZADE", "CHAPÉU", "MOÇA", "DICIONÁRIO", "TREM", "VASO", "CIMENTO", "FLOR", "COMETA", "JOGADA", "ÔNIBUS", "BLUSA", "VICE", "BORBOLETA", "GALO", "HÉLICE", "ABACATE", "VAGALUME", "QUIBE", "CINEMA", "PRATO", "MOLA", "ANDORINHA", "CAPELA", "SOMBRA", "CEREJA", "VARAL", "FACA", "BEXIGA", "DENTE", "SABONETE", "CORUJA", "FOME", "PÁGINA", "FIVELA", "SONHO", "OUVIDO", "LUZ", "PARASITA", "MANETE", "CÉU", "TELEFONE", "PEIXE", "MOLEZA", "TÁXI", "BANANA", "PONTE", "REINO", "LENHA", "DEBATE", "TERRA", "JACARÉ", "CAL", "PACTO", "TELHADO", "CHAVE", "GIRASSOL", "CARTAZ", "GENTE", "BIBLIOTECA", "TAXA", "CAÇAROLA", "HOJE"
    };
    
    public static Set<String> getPalavras(int n){
        Set<String> palavras = new HashSet();
        Random rnd = new Random();
        while(palavras.size()<n && palavras.size()<D0101.length){
            palavras.add(D0101[rnd.nextInt(D0101.length)]);
        }
        return palavras;
    }
}