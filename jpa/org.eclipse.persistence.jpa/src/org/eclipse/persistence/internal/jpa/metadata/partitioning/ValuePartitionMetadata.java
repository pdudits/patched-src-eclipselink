/*******************************************************************************
 * Copyright (c) 2011, 2012 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0 
 * which accompanies this distribution. 
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at 
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     James Sutherland - initial API and implementation
 *     03/24/2011-2.3 Guy Pelletier 
 *       - 337323: Multi-tenant with shared schema support (part 1)
 ******************************************************************************/  
package org.eclipse.persistence.internal.jpa.metadata.partitioning;

import org.eclipse.persistence.internal.jpa.metadata.ORMetadata;
import org.eclipse.persistence.internal.jpa.metadata.accessors.MetadataAccessor;
import org.eclipse.persistence.internal.jpa.metadata.accessors.objects.MetadataAnnotation;

/**
 * INTERNAL:
 * Define JPA meta-data for partitioning policy.
 * 
 * Key notes:
 * - any metadata mapped from XML to this class must be compared in the
 *   equals method.
 * - any metadata mapped from XML to this class must be initialized in the
 *   initXMLObject method.
 * - when loading from annotations, the constructor accepts the metadata
 *   accessor this metadata was loaded from. Used it to look up any 
 *   'companion' annotation needed for processing.
 * - methods should be preserved in alphabetical order.
 * 
 * @author James Sutherland
 * @since EclipseLink 2.2
 */
public class ValuePartitionMetadata extends ORMetadata {
    protected String connectionPool;
    protected String value;

    /**
     * INTERNAL:
     * Used for XML loading.
     */
    public ValuePartitionMetadata() {
        super("<value-partition>");
    }
    
    /**
     * INTERNAL:
     * Used for annotation loading.
     */
    public ValuePartitionMetadata(MetadataAnnotation annotation, MetadataAccessor accessor) {
        super(annotation, accessor);
        this.connectionPool = (String)annotation.getAttribute("connectionPool");
        this.value = (String)annotation.getAttribute("value");
    }
    
    /**
     * INTERNAL:
     * Used for XML merging and overriding.
     */
    @Override
    public boolean equals(Object objectToCompare) {
        if (objectToCompare instanceof ValuePartitionMetadata) {
            ValuePartitionMetadata partition = (ValuePartitionMetadata) objectToCompare;
            
            return valuesMatch(this.connectionPool, partition.getConnectionPool())
                && valuesMatch(this.value, partition.getValue());
        }
        
        return false;
    }
    
    /**
     * INTERNAL:
     * Used for OX mapping.
     */
    public String getConnectionPool() {
        return connectionPool;
    }

    /**
     * INTERNAL:
     * Used for OX mapping.
     */
    public String getValue() {
        return value;
    }
    
    /**
     * INTERNAL:
     * Used for OX mapping.
     */
    public void setConnectionPool(String connectionPool) {
        this.connectionPool = connectionPool;
    }

    /**
     * INTERNAL:
     * Used for OX mapping.
     */
    public void setValue(String value) {
        this.value = value;
    }
}