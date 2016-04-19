package br.com.eneeyes.archetype.web.result;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Alan on 18/05/2014.
 */
@XmlRootElement
public class ResultCollection<T> extends ResultBase<T> {
    private long page;

    private long offset;

    private long total;

    private T[] items;

    @XmlAttribute
    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    @XmlAttribute
    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    @XmlAttribute
    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    @XmlAttribute
    public T[] getItems() {
        return items;
    }

    public void setItems(T[] items) {
        this.items = items;
    }
}
