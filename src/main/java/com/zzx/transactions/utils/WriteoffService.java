package com.zzx.transactions.utils;



import org.springframework.util.CollectionUtils;

import java.util.*;

public class WriteoffService {

    private final static int drm =1;

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("0");
        list.add("10");
        list.add("11");
        List<List<String>> lists = makePackage(list,drm);
        for (List<String> packages : lists){
            collectBuild(packages);
        }
    }

    public static List<List<String>> makePackage(List<String> list, int size) {
        int sum = list.size();
        int mod = sum % size == 0 ? 0 : 1;
        int num = sum / size + mod;
        List<List<String>> result = new ArrayList<>();
        int i = -1;
        while (i++ < num) {
            if ((i + 1) * size >= sum) {
                result.add(list.subList(i * size, sum));
                break;
            }
            result.add(list.subList(i * size, (i + 1) * size));
        }
        System.out.println(result.size());

        return result;
    }

    public static void collectBuild(List<String> dailyNos) {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("16");
        list.add("3");
        list.add("15");
        list.add("5");
        list.add("13");
        list.add("7");
        list.add("8");
        list.add("12");
        list.add("0");
        list.add("10");
        list.add("11");
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (Integer.parseInt(o1) <= Integer.parseInt(o2)) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        int countMoney = 0;
        for (String daily : dailyNos) {
            countMoney += Integer.parseInt(daily);
        }
        List<Integer> collects = new ArrayList<>();
        boolean success = true;
        while (success) {
            Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                int collectMoney = Integer.parseInt(iterator.next());
                if (countMoney <= collectMoney) {
                    collects.add(collectMoney);
                    success = false;
                    break;
                }
                if (!iterator.hasNext() || CollectionUtils.isEmpty(list)) {
                    collects.add(collectMoney);
                    countMoney = countMoney - collectMoney;
                    iterator.remove();
                }
            }

        }
        System.out.println("日账单：" + dailyNos.toString() + "收款单：" + collects.toString());
    }

}
