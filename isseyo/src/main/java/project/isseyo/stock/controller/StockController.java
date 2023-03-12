package project.isseyo.stock.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.isseyo.stock.dto.StockDto;
import project.isseyo.stock.service.StockService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping("/stockList")
    public String findStockList(Model model){
        List<StockDto> stockList = stockService.findAllStock();
        model.addAttribute("stockList",stockList);
        return "pages/stock/stockList";
    }

    @GetMapping("/stock")
    public String saveStockForm(){
        return "pages/stock/stockForm";
    }

    @PostMapping("/stock")
    public String saveStock(StockDto stockDto){
        stockService.saveStock(stockDto);
        return "redirect:/stockList";
    }

    @GetMapping("/stockDetail/{id}")
    public String findByStock(Model model, @PathVariable("id") int id){
        StockDto dto = stockService.findByStock(id);
        System.out.println(dto);
        model.addAttribute("dto",dto);
        return "pages/stock/stockDetail";
    }

    @GetMapping("/modifyStockForm/{id}")
    public String modifyStockForm(Model model ,@PathVariable("id") int id){
        StockDto dto = stockService.findByStock(id);
        System.out.println(dto);
        model.addAttribute("dto",dto);
        return "pages/stock/modifyStockForm";
    }
    
    @PostMapping("/modifyStock")
    public String modifyStock(StockDto stockDto){
        int id = stockService.update(stockDto);
        System.out.println("id ::: " + id);

        return "redirect:/stockDetail/" + id;
    }
    
    @GetMapping("/stock/{id}")
    public String deleteStock(@PathVariable("id") int id){
        stockService.delete(id);
        System.out.println("탐");
        return "redirect:/stockList";
    }


}
