package homework;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;

public class Table {
    private Node root;
    private boolean inserted;
    private boolean deleted;
    private int counter = 0;
    private LinkedList<String> output = new LinkedList<String>();
    Table() {
        this.root = null;
    }
    public Boolean readFile(String fileName) throws IOException {
        FileReader fr = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/homework/"+fileName+".txt"));
            String line = null;
            StringBuilder  stringBuilder = new StringBuilder();
            String ls = System.getProperty("line.separator");
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            String sb = stringBuilder.toString();
            List<String> lines = new LinkedList<String>(Arrays.asList(sb.split("\n")));
            lines.removeAll(Arrays.asList(""));
            for(int i=0; i<lines.size(); i+=2) {
                String key = lines.get(i);
                String value = lines.get(i+1);
                insert(key, value);
            }
        } catch(Exception e) {
            return false;
        } finally {
            if(fr != null) {
                try{
                    fr.close();
                }catch (Exception e2) {
                    throw new RuntimeException("failed");
                }
            }
        }
        return true;
    }
    public boolean insert(String key, String value) {
        this.inserted = true;
        Node newNode = new Node(key, value);
        this.root = insertNode(this.root, newNode);
        return this.inserted;
    }



    public Node insertNode(Node parent, Node newNode) {
        if (parent == null) {
            return newNode;
        }
        if (parent.compareTo(newNode) > 0) {
            parent.setLeft(insertNode(parent.getLeft(), newNode));
        } else if (parent.compareTo(newNode) < 0) {
            parent.setRight(insertNode(parent.getRight(), newNode));
        } else {
            this.inserted = false;
        }
        return parent;
    }
    public void TextToFile(final String strFilename, final LinkedList<String> output) {
        try {
            // 创建文件对象
            File fileText = new File("src/homework/", strFilename+".txt");
            // 向文件写入对象写入信息
            FileWriter fileWriter = new FileWriter(fileText);

            // 写文件
            for (String obj : output) {
                fileWriter.write(obj);
            }
//            Iterator i = output.iterator();
//            while (i.hasNext()) {
//                String obj = (String)i.next();
//                fileWriter.write(obj);
//            }
            // 关闭
            fileWriter.close();
        } catch (IOException e) {
            //
            e.printStackTrace();
        }
    }
    public void save(String filename) {
        Inorder(this.root);
        TextToFile(filename, this.output);
        this.output = new LinkedList<String>();
    }
    public boolean delete(String key) {
        this.deleted = false;
        this.root = deleteNode(this.root, key);
        return this.deleted;
    }
    public Node deleteNode(Node parent, String key) {
        if (parent == null) {
            return null;
        }
        if (parent.getKey().compareTo(key) > 0) {
            parent.setLeft(deleteNode(parent.getLeft(), key));
        } else if (parent.getKey().compareTo(key) < 0) {
            parent.setRight(deleteNode(parent.getRight(), key));
        } else if (parent.getKey().compareTo(key) == 0) {
            if (parent.getRight() == null) {
                this.deleted = true;
                return parent.getLeft();
            } else if (parent.getLeft() == null) {
                this.deleted = true;
                return parent.getRight();
            } else {
                parent.setKey(minValue(parent.getRight()));
                parent.setRight(deleteNode(parent.getRight(), parent.getKey()));
                this.deleted = true;
            }
        }
        return parent;
    }

    public String minValue(Node parent) {
        String min = parent.getKey();
        while (parent.getLeft() != null) {
            min = parent.getLeft().getKey();
            parent.setLeft(parent.getLeft());
        }
        return min;
    }
    public String lookUp(String key) {
        Node node = lookUpNode(this.root, key);
        if (node == null) {
            return null;
        }
        return node.getValue();
    }
    public Node lookUpNode(Node parent, String key) {
        if (parent == null || parent.getKey().equals(key)) {
            return parent;
        }
        if (parent.getKey().compareTo(key) > 0) {
            return lookUpNode(parent.getLeft(), key);
        } else {
            return lookUpNode(parent.getRight(), key);
        }
    }

    public boolean update(String key, String newValue) {
        Node node = lookUpNode(this.root, key);
        if (node == null) {
            return false;
        }
        node.setValue(newValue);
        return true;
    }

    public int displayAll() {
        int count = printInorder(this.root);
        this.counter = 0;
        return count;
    }
    public Node Inorder(Node parent) {
        if (parent == null) {
            return null;
        }
        Inorder(parent.getLeft());
        this.output.add(parent.getKey());
        this.output.add("\n");
        this.output.add(parent.getValue());
        this.output.add("\n");
        this.output.add("\n");
        Inorder(parent.getRight());
        return parent;
    };
    public int printInorder(Node parent) {
        if (parent == null) {
            return this.counter;
        }
        printInorder(parent.getLeft());
        this.counter++;
        System.out.println(parent.toString());
        System.out.println();
        printInorder(parent.getRight());
        return this.counter;
    }
}