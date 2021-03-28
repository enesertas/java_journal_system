import java.io.*;
import java.util.*;



public class Main {

    //Methods
    public static Article findMatchingArticle(String articleId, ArrayList<Article> articleArray) {
        Article foundArticle = null;
        for (int i = 0; i < articleArray.size(); i++) {
            if (articleId.equals(articleArray.get(i).getPaperId())) {
                foundArticle = articleArray.get(i);
            }
        }
        return foundArticle;
    }
    public static String[] sortFiveArticles(String article1, String article2, String article3, String article4,
                                        String article5){
        String[] articles = new String [5];
        articles[0] = article1;
        articles[1] = article2;
        articles[2] = article3;
        articles[3] = article4;
        articles[4] = article5;
        Arrays.sort(articles);
        return articles;
    }

    public static void main(String[] args) throws IOException{

        BufferedWriter bw = new BufferedWriter(
                new FileWriter("output.txt")
        );
        String authorFileName = args[0];
        String commandFileName = args[1];
        //Taking Author input and Constructing It
        File authorFile = new File(authorFileName);
        Scanner scanAuthor = new Scanner(authorFile);
        ArrayList<String> authorFileList = new ArrayList<String>();
        String authorDataFull = "";
        while (scanAuthor.hasNextLine()) {
            String authorData = scanAuthor.nextLine();
            authorDataFull = authorDataFull + authorData + "\n";
        }

        String[] authorDataLines = authorDataFull.split("\n");

        ArrayList<Author> authArray=new ArrayList<Author>();

        for (int i = 0; i < authorDataLines.length; i++) {
            String[] parts = authorDataLines[i].split(" ");

            //This is where the construction of authors being done
            if (parts.length == 7) {
                authArray.add(new Author(parts[1], parts[2], parts[3], parts[4], parts[5],
                        parts[6]));
            }else if (parts.length == 6)  {
                authArray.add(new Author(parts[1], parts[2], parts[3], parts[4], parts[5]));
            }else if (parts.length == 8) {
                authArray.add(new Author(parts[1], parts[2], parts[3], parts[4], parts[5],
                        parts[6], parts[7]));
            } else if (parts.length == 9) {
                authArray.add(new Author(parts[1], parts[2], parts[3], parts[4], parts[5],
                        parts[6], parts[7], parts[8]));
            } else if (parts.length == 10) {
                authArray.add(new Author(parts[1], parts[2], parts[3], parts[4], parts[5],
                        parts[6], parts[7], parts[8], parts[9]));
            } else if (parts.length == 11) {
                authArray.add(new Author(parts[1], parts[2], parts[3], parts[4], parts[5],
                        parts[6], parts[7], parts[8], parts[9], parts[10]));
            } else if (parts.length == 3) {
                authArray.add(new Author(parts[1], parts[2]));
            }
        }
        // Author Object Construction Done



        // Taking Commands
        File commandFile = new File(commandFileName);
        Scanner scanCommand = new Scanner(commandFile);
        String commandsFull = "";
        ArrayList<Article> artArray=new ArrayList<Article>();
        while (scanCommand.hasNextLine()){
            String command = scanCommand.nextLine();

            // Main logic will happen here

            if (command.contains("read")) {
                // Reading the article file
                String[] fullCommand = command.split(" ");
                File articleFile = new File(fullCommand[1]);
                Scanner scanArticle = new Scanner(articleFile);
                String articlesFull = "";
                while(scanArticle.hasNextLine()){
                    articlesFull = articlesFull + scanArticle.nextLine() + "\n";
                }
                String[] articleDataLines = articlesFull.split("\n");
                // Article construction will happen now
                for (int i = 0; i < articleDataLines.length; i ++){
                    String[] partsArticle = articleDataLines[i].split(" ");
                    artArray.add(new Article(partsArticle[1], partsArticle[2], partsArticle[3], partsArticle[4]));
                }
            }else if (command.equals("list")) {
                bw.write("----------------------------------------------List---------------------------------------------\n");
                for (int i = 0; i < authArray.size(); i++) {
                    Author currObj = authArray.get(i);
                    bw.write(String.format("%s:%s\t%s\t%s\t%s\t%s\n", "Author", currObj.getId(), currObj.getName(),
                            currObj.getUniversity(), currObj.getDepartment(), currObj.getEmail()));
                    if (currObj.getArticle1().length() > 0) {
                        Article currArticle = findMatchingArticle(currObj.getArticle1(), artArray);
                        bw.write(String.format("+%s\t%s\t%s\t%s\n", currArticle.getPaperId(), currArticle.getName(),
                                currArticle.getPublisherName(), currArticle.getPublishYear()));
                    }
                    if (currObj.getArticle2().length() > 0) {
                        Article currArticle = findMatchingArticle(currObj.getArticle2(), artArray);
                        bw.write(String.format("+%s\t%s\t%s\t%s\n", currArticle.getPaperId(), currArticle.getName(),
                                currArticle.getPublisherName(), currArticle.getPublishYear()));
                    }
                    if (currObj.getArticle3().length() > 0) {
                        Article currArticle = findMatchingArticle(currObj.getArticle3(), artArray);
                        bw.write(String.format("+%s\t%s\t%s\t%s\n", currArticle.getPaperId(), currArticle.getName(),
                                currArticle.getPublisherName(), currArticle.getPublishYear()));
                    }
                    if (currObj.getArticle4().length() > 0) {
                        Article currArticle = findMatchingArticle(currObj.getArticle4(), artArray);
                        bw.write(String.format("+%s\t%s\t%s\t%s\n", currArticle.getPaperId(), currArticle.getName(),
                                currArticle.getPublisherName(), currArticle.getPublishYear()));
                    }
                    if (currObj.getArticle5().length() > 0) {
                        Article currArticle = findMatchingArticle(currObj.getArticle5(), artArray);
                        bw.write(String.format("+%s\t%s\t%s\t%s\n", currArticle.getPaperId(), currArticle.getName(),
                                currArticle.getPublisherName(), currArticle.getPublishYear()));
                    }
                    bw.write("\n");
                }

                bw.write("----------------------------------------------End----------------------------------------------\n");
            }else if (command.contains("completeAll")){
                for (int i = 0; i < authArray.size(); i++) {
                    // Checking if the objects article attributes are already full
                    if (!authArray.get(i).getArticle1().equals("") && !authArray.get(i).getArticle2().equals("") &&
                            !authArray.get(i).getArticle3().equals("") && !authArray.get(i).getArticle4().equals("") &&
                            !authArray.get(i).getArticle5().equals("")) {
                        continue;
                    }
                    for (int j = 0; j < artArray.size(); j++) {
                        // Checking if first 3 chars of paperId matches with authorId
                        if (authArray.get(i).getId().equals(artArray.get(j).getPaperId().substring(0, 3))) {
                            // Checking if the object already has that article
                            if (authArray.get(i).getArticle1().equals(artArray.get(j).getPaperId())) {
                                continue;
                            }else if(authArray.get(i).getArticle2().equals(artArray.get(j).getPaperId())) {
                                continue;
                            }else if(authArray.get(i).getArticle3().equals(artArray.get(j).getPaperId())) {
                                continue;
                            }else if(authArray.get(i).getArticle4().equals(artArray.get(j).getPaperId())) {
                                continue;
                            }else if(authArray.get(i).getArticle5().equals(artArray.get(j).getPaperId())) {
                                continue;
                            }else{
                                // Adding appropriate articles to empty article attributes
                                if (authArray.get(i).getArticle1().equals("")) {
                                    authArray.get(i).setArticle1(artArray.get(j).getPaperId());
                                    continue;
                                }else if(authArray.get(i).getArticle2().equals("")){
                                    authArray.get(i).setArticle2(artArray.get(j).getPaperId());
                                    continue;
                                }else if(authArray.get(i).getArticle3().equals("")){
                                    authArray.get(i).setArticle3(artArray.get(j).getPaperId());
                                    continue;
                                }else if(authArray.get(i).getArticle4().equals("")){
                                    authArray.get(i).setArticle4(artArray.get(j).getPaperId());
                                    continue;
                                }else if(authArray.get(i).getArticle5().equals("")){
                                    authArray.get(i).setArticle5(artArray.get(j).getPaperId());
                                    continue;
                                }
                            }
                        }
                    }
                }
                bw.write("*************************************CompleteAll Successful*************************************\n");
            }else if(command.contains("sort")){
                for (int i = 0; i < authArray.size(); i++) {
                    String [] articleArrayStrings = sortFiveArticles(authArray.get(i).getArticle1(),
                            authArray.get(i).getArticle2(), authArray.get(i).getArticle3(),
                            authArray.get(i).getArticle4(), authArray.get(i).getArticle5());
                    authArray.get(i).setArticle1(articleArrayStrings[0]);
                    authArray.get(i).setArticle2(articleArrayStrings[1]);
                    authArray.get(i).setArticle3(articleArrayStrings[2]);
                    authArray.get(i).setArticle4(articleArrayStrings[3]);
                    authArray.get(i).setArticle5(articleArrayStrings[4]);
                }
                bw.write("*************************************SortedAll Successful*************************************\n");
            }else if(command.contains("del")) {
                String idToDeleted = command.split(" ")[1];
                int idIndex = 0;
                for (int i = 0; i < authArray.size(); i++) {
                    if (idToDeleted.equals(authArray.get(i).getId())) {
                        break;
                    } else {
                        idIndex += 1;
                    }
                }
                authArray.remove(idIndex);
                bw.write("*************************************del Successful*************************************\n");

            }else{
                System.out.println("wrong command!");
            }
        };
        bw.close();
        }

    }