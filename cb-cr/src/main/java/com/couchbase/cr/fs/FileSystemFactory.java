/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.couchbase.cr.fs;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import org.apache.jackrabbit.core.fs.FileSystem;
import org.apache.jackrabbit.core.fs.local.LocalFileSystem;
import org.apache.jackrabbit.core.fs.mem.MemoryFileSystem;

/**
 *
 * @author David Maier <david.maier at couchbase.com>
 */
public class FileSystemFactory {
    
    private static final Logger LOG = Logger.getLogger(FileSystemFactory.class.getName());
    
    //Available File Systems
    public static final int MEM = -1;
    public static final int LOCAL = 0;

    
    //Selected File System
    public static final int FS = LOCAL;
    
    //The FS instance
    private static final Map<File, FileSystem> fileSystems = new HashMap<>();
    
    /**
     * Create a file system dependent on the configuration
     * 
     * @param root
     * @return 
     */
    public static FileSystem create(File root)
    {
        if (FS == LOCAL)
        {
            LOG.finest("Using a local file system ...");
            
            LocalFileSystem fs = new LocalFileSystem();
            fs.setRoot(root);
           
            fileSystems.put(root,fs);
            
            return fs;
        }
        
        LOG.finest("Using the default in-memory file system");
        
        //By default return an in memory file system for every root path
        return new MemoryFileSystem();
    }
    
    /**
     * Get an instance of a file system dependent on the configuration
     * 
     * @param root
     * @return 
     */
    public static FileSystem instance(File root)
    {
        if (fileSystems.get(root) == null)
        {
            create(root);
        }
        
        return fileSystems.get(root);
    }
    
}
