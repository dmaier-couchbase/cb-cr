package com.couchbase.cr.persistence;

import com.couchbase.cr.fs.FileSystemFactory;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jackrabbit.core.fs.FileSystem;
import org.apache.jackrabbit.core.fs.FileSystemException;
import org.apache.jackrabbit.core.id.NodeId;
import org.apache.jackrabbit.core.id.PropertyId;
import org.apache.jackrabbit.core.persistence.AbstractPersistenceManager;
import org.apache.jackrabbit.core.persistence.PMContext;
import org.apache.jackrabbit.core.persistence.util.BLOBStore;
import org.apache.jackrabbit.core.persistence.util.FileSystemBLOBStore;
import org.apache.jackrabbit.core.state.ItemStateException;
import org.apache.jackrabbit.core.state.NoSuchItemStateException;
import org.apache.jackrabbit.core.state.NodeReferences;
import org.apache.jackrabbit.core.state.NodeState;
import org.apache.jackrabbit.core.state.PropertyState;

/**
 * This is an implementiation of Jackrabbits Persistence Manager
 * by using Couchbase as the underlying meta data store
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class CBPersistenceManager extends AbstractPersistenceManager {

    private static final Logger LOG = Logger.getLogger(CBPersistenceManager.class.getName());
    
    /**
     * Is the manager already initialized?
     */
    private boolean initalized = false;
    
    /**
     * The file system used by this persistence manager
     */
    private FileSystem fs;
    
    /**
     * The blob store used by this persistence manager
     */
    private BLOBStore blobs;
    
    
    /**
     * Initialize the Persistence Manager
     * @param context
     * @throws Exception 
     */
    @Override
    public void init(PMContext context) throws Exception {
        
        if (!this.initalized)
        {
            //Init the FS
            File homeDir = context.getHomeDir();
            LOG.log(Level.FINEST, "Using home dir {0}", homeDir.toString());
            initFS(homeDir);
            
            //Init Couchbase
            //TODO
            
            this.initalized = true;
        }
        else
        {
            throw new IllegalStateException("Already initialized");
        }
    }
    
    /**
     * Initialize the FS
     * 
     * @param homeDir
     * @throws FileSystemException 
     */
    private void initFS(File homeDir) throws FileSystemException
    {        
            //Use a FileSystemFactory in order get a File system config
            //Init it and the blob store based on it
            this.fs = FileSystemFactory.instance(new File(homeDir, "blobs"));
            this.fs.init();
            this.blobs = new FileSystemBLOBStore(fs);
    }
    
   /**
    * Initialize Couchbase
    */ 
   private void initCB()
   {
       //TODO
       
   }
    
    @Override
    public void close() throws Exception {

        if (this.initalized)
        {
            //Close the FS
            this.fs.close();
            
            //Close Couchbase
            //TODO
        }
        else
        {
            throw new IllegalStateException("Not yet initialized");
        }
    }
    
    @Override
    protected void store(NodeState ns) throws ItemStateException {
        
        if (this.initalized)
        {
            
        }
        else
        {
            throw new IllegalStateException("Not yet initialized");
        }
        
    }

    @Override
    protected void store(PropertyState ps) throws ItemStateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void store(NodeReferences nr) throws ItemStateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void destroy(NodeState ns) throws ItemStateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void destroy(PropertyState ps) throws ItemStateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void destroy(NodeReferences nr) throws ItemStateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public NodeState load(NodeId nodeid) throws NoSuchItemStateException, ItemStateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PropertyState load(PropertyId pi) throws NoSuchItemStateException, ItemStateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NodeReferences loadReferencesTo(NodeId nodeid) throws NoSuchItemStateException, ItemStateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean exists(NodeId nodeid) throws ItemStateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean exists(PropertyId pi) throws ItemStateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existsReferencesTo(NodeId nodeid) throws ItemStateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
