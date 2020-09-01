import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrammarExercise {
    public static void main(String[] args) {
        //需要从命令行读入
        String firstWordList = "";
        String secondWordList = "";
        Scanner in =new Scanner(System.in);
        firstWordList=in.nextLine();
        secondWordList=in.nextLine();
        List<String> result = findCommonWordsWithSpace(firstWordList,secondWordList);
        //按要求输出到命令行
        for(int i=0;i<result.size();i++)
        {
            System.out.print(result.get(i));
        }

    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        //在这编写实现代码
        String[] arrfirt=firstWordList.split(",");
        String[] arrsec=secondWordList.split(",");

        List<String> listfir=Arrays.asList(arrfirt);
        List<String> listsec=Arrays.asList(arrsec);

        //判断输入是否合理
        List<String> firstEmpty=listfir.stream().filter(String::isEmpty).collect(Collectors.toList());
        List<String> secEmpty=listsec.stream().filter(String::isEmpty).collect(Collectors.toList());
        List<String> firNotLetter=listfir.stream().filter(string->string.matches(".*[^a-zA-Z]+.*")).collect(Collectors.toList());
        List<String> secNotLetter=listsec.stream().filter(string->string.matches(".*[^a-zA-Z]+.*") ).collect(Collectors.toList());

        if(firstEmpty.size()+secEmpty.size()+firNotLetter.size()+secNotLetter.size()>0)
            throw new RuntimeException("input not valid");

        List<String> l1=listfir.stream().map(String::toUpperCase).distinct().collect(Collectors.toList());
        List<String> l2=listsec.stream().map(String::toUpperCase).filter(l1::contains).distinct().collect(Collectors.toList());
        List<String> res=l2.stream().map(str->str.replace(""," ").trim()).collect(Collectors.toList());
        return res;
    }
}
