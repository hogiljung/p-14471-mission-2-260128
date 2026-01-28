package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int lastId = 0;
    List<WiseSaying> wiseSayingList = new ArrayList<>();
    String regex = "^id=([0-9]*)$";
    Pattern p = Pattern.compile(regex);

    public void run() throws IOException {
        System.out.println("== 명언 앱 ==");
        while (true) {
            System.out.print("명령) ");
            String cmdLine = br.readLine();
            String[] cmd = cmdLine.split("\\?");
            switch (cmd[0]) {
                case "종료":
                    return;
                case "등록":
                    addWiseSaying();
                    break;
                case "목록":
                    showWiseSayingList();
                    break;
                case "삭제":
                    deleteWiseSaying(cmd[1]);
                    break;
                case "수정":
                    updateWiseSaying(cmd[1]);
                    break;
                default:
                    System.out.println("해당 명령어는 존재하지 않습니다.");
            }
        }
    }

    private void addWiseSaying() throws IOException {
        System.out.print("명언: ");
        String content = br.readLine();
        System.out.print("작가: ");
        String author = br.readLine();

        WiseSaying q = new WiseSaying(++lastId, content, author);
        wiseSayingList.add(q);

        System.out.println(q.id + "번 명언이 등록되었습니다.");
    }

    private void showWiseSayingList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for (int i = wiseSayingList.size() - 1; i >= 0; i--) {
            System.out.println(wiseSayingList.get(i).getInfo());
        }
    }

    private void deleteWiseSaying(String arg) {
        Matcher m = p.matcher(arg);
        if (!m.find()) {
            System.out.println("형식이 맞지 않습니다.");
            return;
        }

        String idStr = m.group(1);
        int id = Integer.parseInt(idStr);

        int i = getWiseSayingIdx(id);
        if (i == -1) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }
        wiseSayingList.remove(i);
        System.out.println(id + "번 명언이 삭제되었습니다.");
    }

    private void updateWiseSaying(String arg) throws IOException {
        Matcher m = p.matcher(arg);
        if (!m.find()) {
            System.out.println("형식이 맞지 않습니다.");
            return;
        }

        String idStr = m.group(1);
        int id = Integer.parseInt(idStr);
        int i = getWiseSayingIdx(id);
        if (i == -1) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }

        WiseSaying w = getWiseSaying(i);

        System.out.println("명언(기존) : " + w.content);
        System.out.print("명언: ");
        w.content = br.readLine();
        System.out.println("작가(기존) : " + w.author);
        System.out.print("작가: ");
        w.author = br.readLine();

        wiseSayingList.set(i, w);
    }

    private int getWiseSayingIdx(int id) {
        for (int i = 0; i < wiseSayingList.size(); i++) {
            if (wiseSayingList.get(i).id == id) {
                return i;
            }
        }
        return -1;
    }

    private WiseSaying getWiseSaying(int idx) {
        return wiseSayingList.get(idx);
    }
}
