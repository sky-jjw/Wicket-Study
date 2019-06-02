package njupt.link;

import org.apache.wicket.util.io.IClusterable;

//实现可集群接口
public class Book implements IClusterable {
    private String title;

    public Book(final String title){
        this.title=title;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title) {
        this.title=title;
    }

}
