package ru.curs.celesta.score.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Objects;

/**
 * Score resource located in the file system.
 *
 * @author Pavel Perminov (packpaul@mail.ru)
 * @since 2019-02-23
 */
public final class FileResource implements Resource {

    private final File file;
    private final File canonicalFile;

    public FileResource(File file) {
        this.file = file;
        this.canonicalFile = getCanonicalFile();
    }

    private File getCanonicalFile() {
        try {
            return file.getCanonicalFile();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return Files.newInputStream(file.toPath());
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return Files.newOutputStream(file.toPath());
    }

    @Override
    public boolean contains(Resource childResource) {
        if (!(childResource instanceof FileResource)) {
            return false;
        }

        File childFile = ((FileResource) childResource).file;
        try {
            return childFile.getCanonicalPath().startsWith(
                    file.getCanonicalPath() + File.separator);
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public String getRelativePath(Resource childResource) {
        if (!this.contains(childResource)) {
            return null;
        }

        File childFile = ((FileResource) childResource).file;

        return file.toPath().relativize(childFile.toPath()).toString();
    }

    @Override
    public FileResource createRelative(String relativePath) throws IOException {
        return new FileResource(new File(this.file, relativePath));
    }

    @Override
    public String toString() {
        return file.toString();
    }

    @Override
    public int hashCode() {
        return (canonicalFile != null) ? canonicalFile.hashCode() : file.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }

        FileResource other = (FileResource) obj;

        return (canonicalFile != null && other.canonicalFile != null)
                ? Objects.equals(canonicalFile, other.canonicalFile) : Objects.equals(file, other.file);
    }

}