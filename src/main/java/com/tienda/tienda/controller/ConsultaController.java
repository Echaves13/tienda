package com.tienda.tienda.controller;

import com.tienda.tienda.service.ProductoService;
import java.math.BigDecimal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.tienda.tienda.service.CategoriaService;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public ConsultaController(ProductoService productoService,
            CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }
      
    @ModelAttribute("categorias")
    public Object getCategorias() {
        return categoriaService.getCategorias(true);
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        var productos = productoService.getProductos(false);
        model.addAttribute("productos", productos);
        return "/consultas/listado";
    }

    @PostMapping("/consultaDerivada")
    public String consultaDerivida(@RequestParam() BigDecimal precioInf,
            @RequestParam() BigDecimal precioSup,
            Model model) {
        var productos = productoService.consultaDerivada(precioInf, precioSup);
        model.addAttribute("productos", productos);
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        return "/consultas/listado";
    }

    @PostMapping("/consultaJPQL")
    public String consultaJPQL(@RequestParam() BigDecimal precioInf,
            @RequestParam() BigDecimal precioSup,
            Model model) {
        var productos = productoService.consultaJPQL(precioInf, precioSup);
        model.addAttribute("productos", productos);
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        return "/consultas/listado";
    }

    @PostMapping("/consultaSQL")
    public String consultaSQL(@RequestParam() BigDecimal precioInf,
            @RequestParam() BigDecimal precioSup,
            Model model) {
        var productos = productoService.consultaSQL(precioInf, precioSup);
        model.addAttribute("productos", productos);
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        return "/consultas/listado";
    }
    
    @PostMapping("/consultaInventario")
    public String consultaInventario(
            @RequestParam(required = false) Integer idCategoria,
            @RequestParam() Integer existenciasMin,
            @RequestParam() Integer existenciasMax,
            Model model) {

        var productos = productoService.consultaInventario(
                idCategoria, existenciasMin, existenciasMax);

        model.addAttribute("productos", productos);
        model.addAttribute("idCategoria", idCategoria);
        model.addAttribute("existenciasMin", existenciasMin);
        model.addAttribute("existenciasMax", existenciasMax);

        return "/consultas/listado";
    }

}
