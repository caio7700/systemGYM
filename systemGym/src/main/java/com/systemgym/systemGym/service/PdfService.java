package com.systemgym.systemgym.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.systemgym.systemgym.entity.Aluno;
import com.systemgym.systemgym.entity.Treino;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class PdfService {

    @SuppressWarnings("ConvertToTryWithResources")
    public void gerarFichaTreino(HttpServletResponse response, Aluno aluno, List<Treino> treinos) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        
        // Fonte para o Título
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
        Paragraph title = new Paragraph("SYSTEMGYM - FICHA DE TREINO", fontTitle);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        document.add(new Paragraph(" ")); // Espaço
        document.add(new Paragraph("Aluno: " + aluno.getNome()));
        document.add(new Paragraph("Data: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        document.add(new Paragraph(" "));

        // Criar Tabela
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        
        // Cabeçalhos
        table.addCell("Exercício");
        table.addCell("Séries");
        table.addCell("Repetições");
        table.addCell("Carga");

        for (Treino t : treinos) {
            table.addCell(t.getExercicio().getNome());
            table.addCell(t.getSeries());
            table.addCell(t.getRepeticoes());
            table.addCell(t.getCarga());
        }

        document.add(table);
        document.close();
    }

}
