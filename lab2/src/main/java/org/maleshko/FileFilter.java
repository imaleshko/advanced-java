package org.maleshko;

import java.nio.file.Path;

@FunctionalInterface
public interface FileFilter {
    boolean match(Path path);
}
