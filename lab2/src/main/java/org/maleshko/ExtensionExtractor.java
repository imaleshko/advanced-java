package org.maleshko;

import java.nio.file.Path;

@FunctionalInterface
public interface ExtensionExtractor {
    String extract(Path path);
}
