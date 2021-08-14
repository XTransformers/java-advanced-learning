package com.xtransformers.designpattern.momento.mine;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author daniel
 * @date 2021-08-14
 */
public class InputText {

    private static final LinkedList<String> LIST = Lists.newLinkedList();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (StringUtils.equals(line, ":quit")) {
                System.out.println("bye bye.");
                break;
            } else if (StringUtils.equals(line, ":list")) {
                for (String word : LIST) {
                    System.out.print(word);
                }
                System.out.println();
            } else if (StringUtils.equals(line, ":undo")) {
                LIST.removeLast();
            } else {
                LIST.add(line);
            }
        }
    }

}
