package GameData;


import Entities.EntityData;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.*;

public class FileSettingsParser implements ISettingsParser{
    private int FPS;
    private Dimension sizeWindow;
    Map<FloatEnum,Float> floatInput;
    Map<EntityEnum,EntityData> entitiyDataInput;

    public FileSettingsParser(){
        Pattern pFloats = Pattern.compile("(\\w+): (\\d+\\.?\\d*f?)");
        Pattern pEntity = Pattern.compile("(\\w+): \\((\\d+\\.?\\d*f?),(\\d+\\.?\\d*f?),(\\d+\\.?\\d*f?)\\)");
        Pattern pDimension= Pattern.compile("\\w+: (\\d+)x(\\d+)");

        Map<String,FloatEnum> floatVal = new TreeMap<>();
        Map<String,EntityEnum>entityVal = new TreeMap<>();
        for(FloatEnum v :FloatEnum.values()) floatVal.put(v.name(),v);
        for(EntityEnum v :EntityEnum.values())entityVal.put(v.name(),v);

        BufferedReader inputBuffer=null;
        try {
            InputStream fileStream = FileSettingsParser.class.getResourceAsStream("GameSettings.txt");
            if(fileStream==null)failure();
            inputBuffer = new BufferedReader(new InputStreamReader(fileStream));
            String line = inputBuffer.readLine();
            Matcher pf = pFloats.matcher(line);
            if(!pf.matches())failure();
            FPS = Integer.parseInt(pf.group(2));
            Matcher window = pDimension.matcher(inputBuffer.readLine());
            if(!window.matches())failure();
            sizeWindow = new Dimension(Integer.parseInt(window.group(1)), Integer.parseInt(window.group(2)));

            floatInput = new TreeMap<>();
            entitiyDataInput = new TreeMap<>();
            while(inputBuffer.ready()){
                line = inputBuffer.readLine();
                Matcher mFloat = pFloats.matcher(line);
                Matcher mEntity = pEntity.matcher(line);
                if(mFloat.matches()&& floatVal.containsKey(mFloat.group(1))){
                    floatInput.putIfAbsent(floatVal.get(mFloat.group(1)),Float.parseFloat(mFloat.group(2)));
                }
                else if(mEntity.matches()&& entityVal.containsKey(mEntity.group(1))){
                    float h,d,s;
                    h = Float.parseFloat(mEntity.group(2));
                    d = Float.parseFloat(mEntity.group(3));
                    s = Float.parseFloat(mEntity.group(4));
                    entitiyDataInput.putIfAbsent(entityVal.get(mEntity.group(1)),new EntityData(h,d,s));
                }
            }
        }catch (IOException | IndexOutOfBoundsException e){
            failure();
        }
        finally {
            try {
                if (inputBuffer != null) inputBuffer.close();
            }catch(IOException e){
                failure();
            }
            for(Map.Entry<FloatEnum,Float> f: floatInput.entrySet())System.out.println(f);
            for(Map.Entry<EntityEnum,EntityData> e: entitiyDataInput.entrySet())System.out.println(e);
        }


    }

    private void failure() {
        JOptionPane.showMessageDialog(null, "Error: Archivo GameSettings.txt no existe o tiene errores de formato", "Error game settings", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }

    public EntityData getEntityData(EntityEnum ref){
        if(!entitiyDataInput.containsKey(ref))System.out.println(ref);
       return entitiyDataInput.getOrDefault(ref,new EntityData(0,0,0));
    }
    public float getFloat(FloatEnum ref){
        if(!floatInput.containsKey(ref))System.out.println(ref);
        return floatInput.getOrDefault(ref,0f);
    }
    public int FPS(){
        return FPS;
    }
    public Dimension sizeWindow(){
        return sizeWindow;
    }
}

