package kr.jonghyun.system;

import java.io.File;

public interface FileLoader<T> {

    T read(File file);

}
