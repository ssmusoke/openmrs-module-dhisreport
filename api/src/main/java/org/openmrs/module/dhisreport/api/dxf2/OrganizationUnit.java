package org.openmrs.module.dhisreport.api.dxf2;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "organisationUnit" )
public class OrganizationUnit
{
    private String name;

    private String code;

    @XmlAttribute
    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    @XmlAttribute
    public String getCode()
    {
        return code;
    }

    public void setCode( String code )
    {
        this.code = code;
    }
}
