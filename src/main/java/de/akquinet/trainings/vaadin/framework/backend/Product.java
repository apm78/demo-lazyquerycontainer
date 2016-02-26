package de.akquinet.trainings.vaadin.framework.backend;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
public class Product
{
    private long id;
    private String name;
    private String modelNumber;

    public Product()
    {

    }

    public Product(final long id, final String name, final String modelNumber)
    {
        this.name = name;
        this.modelNumber = modelNumber;
        this.id = id;
    }

    public long getId()
    {
        return id;
    }

    public void setId(final long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public String getModelNumber()
    {
        return modelNumber;
    }

    public void setModelNumber(final String modelNumber)
    {
        this.modelNumber = modelNumber;
    }
}
