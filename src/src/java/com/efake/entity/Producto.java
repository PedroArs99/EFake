package com.efake.entity;

import com.efake.dto.KeywordsDTO;
import com.efake.dto.ProductoDTO;
import com.efake.dto.ValoracionDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author PedroArenas
 */
@Entity
@Table(name = "PRODUCTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
    , @NamedQuery(name = "Producto.findById", query = "SELECT p FROM Producto p WHERE p.id = :id")
    , @NamedQuery(name = "Producto.findByNombre", query = "SELECT p FROM Producto p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Producto.findByPrecio", query = "SELECT p FROM Producto p WHERE p.precio = :precio")
    , @NamedQuery(name = "Producto.findByFecha", query = "SELECT p FROM Producto p WHERE p.fecha = :fecha")
    , @NamedQuery(name = "Producto.findByOwner", query = "SELECT p FROM Producto p WHERE p.owner = :owner")
    , @NamedQuery(name = "Producto.findByCategoria", query = "SELECT p FROM Producto p WHERE p.categoria = :categoria")
    , @NamedQuery(name = "Producto.findBySubCategoria", query = "SELECT p FROM Producto p WHERE p.subcategoria = :subcategoria")
    , @NamedQuery(name = "Producto.findByFilter", query = "SELECT DISTINCT p FROM Producto p LEFT JOIN p.keywordsList k WHERE p.nombre LIKE :words OR p.descripcion LIKE :words OR k.palabra LIKE :words")
    , @NamedQuery(name = "Producto.findSortedByRatingsNumber", query = "SELECT p FROM Valoracion v JOIN v.productoValorado p GROUP BY v.productoValorado ORDER BY COUNT(v.productoValorado) DESC")
    , @NamedQuery(name = "Producto.CountByDate", query = "SELECT p.fecha, count(p) FROM  Producto p WHERE p.fecha BETWEEN :start and :end GROUP BY p.fecha ORDER BY p.fecha DESC")})
public class Producto implements Serializable {

    @Column(name = "1Estrella")
    private Integer estrella1;
    @Column(name = "2Estrella")
    private Integer estrella2; 
    @Column(name = "3Estrella")
    private Integer estrella3;
    @Column(name = "4Estrella")
    private Integer estrella4;
    @Column(name = "5Estrella")
    private Integer estrella5;
    

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Nombre")
    private String nombre;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Precio")
    private double precio;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Imagen")
    private String imagen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @ManyToMany(mappedBy = "productoList", fetch = FetchType.LAZY)
    private List<Keywords> keywordsList;
    @OneToMany(mappedBy = "productoValorado", fetch = FetchType.LAZY)
    private List<Valoracion> valoracionList;
    @JoinColumn(name = "Categoria", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Categoria categoria;
    @JoinColumn(name = "Owner", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario owner;
    @JoinColumn(name = "Subcategoria", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Subcategoria subcategoria;

    public Producto() {
    }

    public Producto(Integer id) {
        this.id = id;
    }

    public Producto(Integer id, String nombre, double precio, Date fecha) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.fecha = fecha;
    }
    public Producto(ProductoDTO product, boolean stopRecursion){
        this.id= product.getId();
        this.nombre = product.getNombre();
        this.precio = product.getPrecio();
        this.fecha = product.getFecha();
        this.imagen = product.getImagen();
        this.owner = new Usuario(product.getOwner());
        this.descripcion = product.getDescripcion();
        if(!stopRecursion){
            this.keywordsList = product.getlistakeywords();
        }
        
        this.subcategoria = new Subcategoria(product.getSubcategoria());
        this.categoria = new Categoria(product.getCategoria());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @XmlTransient
    public List<Keywords> getKeywordsList() {
        return keywordsList;
    }

    public void setKeywordsList(List<Keywords> keywordsList) {
        this.keywordsList = keywordsList;
    }

    @XmlTransient
    public List<Valoracion> getValoracionList() {
        return valoracionList;
    }

    public void setValoracionList(List<Valoracion> valoracionList) {
        this.valoracionList = valoracionList;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Usuario getOwner() {
        return owner;
    }

    public void setOwner(Usuario owner) {
        this.owner = owner;
    }

    public Subcategoria getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.efake.entity.Producto[ id=" + id + " ]";
    }
    
    public String getKeywordsJSP() {
        StringJoiner sj = new StringJoiner(",");

        for (Keywords k : this.getKeywordsList()) {
            sj.add(k.getPalabra());
        }

        return sj.toString();
    }
    
    public ProductoDTO getDTO(){
        ProductoDTO prodDTO = new ProductoDTO();
        prodDTO.setId(id);
        prodDTO.setNombre(this.nombre);
        prodDTO.setDescripcion(this.descripcion);
        prodDTO.setPrecio(this.precio);
        prodDTO.setImagen(this.imagen);
        prodDTO.setFecha(this.fecha);
        prodDTO.setListaKeywords(keywordslist());
        prodDTO.setCategoria(this.categoria.getDTO());
        prodDTO.setOwner(this.owner.getDTO());
        if(this.subcategoria!=null){
            prodDTO.setSubcategoria(this.subcategoria.getDTO());
        }else{
            prodDTO.setSubcategoria(null);
        }
        
        prodDTO.setListaValoraciones(valoracionlist(prodDTO));
        
        return prodDTO;
    }
    
    public List<KeywordsDTO> keywordslist(){
        List<KeywordsDTO> lista = new ArrayList<>();
        for(Keywords k : this.keywordsList){
            lista.add(k.getDTO());
        }
        return lista;
    }
    
    
    public List<ValoracionDTO> valoracionlist(ProductoDTO prodDTO){
        List<ValoracionDTO> lista = new ArrayList<>();
        for(Valoracion v : this.valoracionList){
            lista.add(v.getDTO(prodDTO));
        }
        return lista;
    }

    public Integer getEstrella1() {
        return estrella1;
    }

    public void setEstrella1(Integer estrella1) {
        this.estrella1 = estrella1;
    }

    public Integer getEstrella2() {
        return estrella2;
    }

    public void setEstrella2(Integer estrella2) {
        this.estrella2 = estrella2;
    }

    public Integer getEstrella3() {
        return estrella3;
    }

    public void setEstrella3(Integer estrella3) {
        this.estrella3 = estrella3;
    }

    public Integer getEstrella4() {
        return estrella4;
    }

    public void setEstrella4(Integer estrella4) {
        this.estrella4 = estrella4;
    }
    
       public Integer getEstrella5() {
        return estrella5;
    }

    public void setEstrella5(Integer estrella5) {
        this.estrella5 = estrella5;
    }
}
