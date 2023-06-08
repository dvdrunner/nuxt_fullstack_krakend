package ea.ciges.krakend.model;

import java.io.Serializable;
import java.util.Objects;

public abstract class EntidadPK<E extends Serializable> implements Serializable, Cloneable, EntidadID<E> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * hashCode. Devuelve el hashCode del objeto
     *
     * @return hashCode
     */
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    /**
     * equals
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || (getClass() != obj.getClass())) {
            return false;
        }
        @SuppressWarnings("unchecked")
        EntidadPK<E> other = (EntidadPK<E>) obj;
        return Objects.equals(getId(), other.getId());
    }

    /**
     * toString. Devuelve el String con la id del objeto.
     *
     * @return String.
     */
    public String toString() {
        return getClass().getSimpleName() + "[" + getId() + "]";
    }

    /**
     * clone. Devuelve un clon del objeto.
     *
     * @return EntidadPK
     */
    @SuppressWarnings("unchecked")
    @Override
    public final EntidadPK<E> clone() throws CloneNotSupportedException {
        return (EntidadPK<E>) super.clone();
    }

}
