package com.example.snotes;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class DataManager {
    public static void save_data(String content, String name, Context context){
        try(FileOutputStream fos = context.openFileOutput(name, Context.MODE_PRIVATE)){
            fos.write(content.getBytes());
        }
        catch (Exception e){}
    }
    public static String get_data(String name, Context context){
        String content = "";
        StringBuilder stringBuilder = new StringBuilder();
        try{
            FileInputStream fis = context.openFileInput(name);
            InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null){
                stringBuilder.append(line).append("\n");
                line = reader.readLine();
            }
        }
        catch (Exception e){}
        finally {
            content = stringBuilder.toString();
        }
        return content;
    }
    public static String[] get_all_files_name(Context context){
        return context.fileList();
    }
    public static void delete_file(Context context, String name){
        File file = new File(context.getFilesDir(), name);
        file.delete();
    }
    public static boolean isNameExist(String name, Context context){
        String[] names = get_all_files_name(context);
        for(int i = 0; i < names.length; i++){
            if(names[i].equals(name)){
                return true;
            }
        }
        return false;
    }
}
