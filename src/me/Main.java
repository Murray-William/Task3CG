package me;

import me.math.Vector2f;
import me.math.Vector3f;
import me.model.Model;
import me.model.Polygon;
import me.objwriter.ObjWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Model  model =  new Model(new ArrayList<Vector3f>(List.of(new Vector3f(1.122f, 2, 3.2321f),
                new Vector3f(3.213f, 4.2f, 5),
                new Vector3f(6, 7.33f, 8.123213f),
                new Vector3f(60, -9.99f, 8.3f))),
                new ArrayList<Vector2f>(List.of(new Vector2f(1.123f, 2f),
                        new Vector2f(3.31f, 4.2f),
                        new Vector2f(0.21f, 14f),
                        new Vector2f(22.1f, 45f))),
                new ArrayList<Vector3f>(List.of(new Vector3f(1.122f, 2, -3.2321f),
                        new Vector3f(3.213f, 4.2f, 5),
                        new Vector3f(6, 7.33f, 8.123213f),
                        new Vector3f(12, 3.33f, 32f))),
                new ArrayList<Polygon>(List.of(new Polygon(List.of(1, 1, 1), List.of(2, 2, 2), List.of(3, 3, 3)),
                        new Polygon(List.of(2, 2, 2), List.of(3, 3, 3), List.of(4, 4, 4)))));

        // 1 вариант - указать путь к папке в которую нужно сохранить obj файл и дать ему название
        ObjWriter.write(model, "..\\Task3CG\\tests\\resources\\objwriter\\output\\", "mainTest");

        // 2 вариант - передать путь к файлу с расширением obj или объект класса File с файлом расширения obj.  ФАЙЛ БУДЕТ ПЕРЕЗАПИСАН
        ObjWriter.write(model, new File("..\\Task3CG\\tests\\resources\\objwriter\\output\\mainTest.obj"));
        ObjWriter.write(model, "..\\Task3CG\\tests\\resources\\objwriter\\output\\mainTest.obj");

        // 3 вариант - универсальный можно передать объект класса наследника от Writer (FileWriter, PrintWriter, StringWriter)
        Writer fw = new FileWriter("..\\Task3CG\\tests\\resources\\objwriter\\output\\mainTest1.obj");
        ObjWriter.write(model, fw);
        fw.close();

        Writer sw = new StringWriter();
        ObjWriter.write(model, sw);
        System.out.println(sw);
        sw.close();

        // можно обернуть System.out в PrintWriter и сразу выводить на консоль
        Writer pw = new PrintWriter(System.out);
        ObjWriter.write(model, pw);
        pw.close();
    }
}















