package perks;


class Perk implements Comparable<Perk> {
    
    /**
     * The name of this perk.
     */
    private String name;
    
    /**
     * The estimated value of this perk at the end of the game
     */
    private int value;
    
    
    public Perk (String n, int v)
    {
        name = n;
        setValue(v);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }

    public String toString()
    {
        return "" + name + ":" + getValue();
    }

    @Override
    public int compareTo(Perk p) {
        return name.compareTo(p.name);
    }
    
    @Override
    public boolean equals(Object o) {
        Perk p = (Perk)o;
        return p.name.equals(name);
    }
    
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}