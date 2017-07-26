package com.gosun.isap.warn.impl.alert.export.word;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.BodyElementType;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFStyle;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageSz;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPageOrientation;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.List;

/**
 * <p>创建时间：2017-5-24 11:31</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class WordOperationHelper {
    /**
     * 复制 document ，copy 行间距有问题
     *
     * @param srcDoc  源 document
     * @param destDoc 目的 document
     */
    public static void copyDocument(XWPFDocument srcDoc, XWPFDocument destDoc) {
        if (srcDoc == null || destDoc == null) {
            return;
        }
        copyLayout(srcDoc, destDoc);
        for (IBodyElement bodyElement : srcDoc.getBodyElements()) {
            BodyElementType elementType = bodyElement.getElementType();
            if (elementType == BodyElementType.PARAGRAPH) {
                XWPFParagraph srcPr = (XWPFParagraph) bodyElement;
                XWPFParagraph dstPr = destDoc.createParagraph();

                CTPPr srcPPr = srcPr.getCTP().getPPr();
                CTPPr destPPr = dstPr.getCTP().addNewPPr();
                copyStyle(srcDoc, destDoc, srcDoc.getStyles().getStyle(srcPr.getStyleID()));
                destPPr.set(srcPPr);
                for (XWPFRun srcRun : srcPr.getRuns()) {
                    // 复制文字
                    XWPFRun destRun = dstPr.createRun();
                    CTRPr ctrPr = destRun.getCTR().addNewRPr();
                    ctrPr.set(srcRun.getCTR().getRPr());
                    destRun.setText(srcRun.getText(srcRun.getTextPosition()), srcRun.getTextPosition());

                    // 复制图片
                    if (srcRun.getEmbeddedPictures().size() > 0) {
                        for (XWPFPicture pic : srcRun.getEmbeddedPictures()) {
                            byte[] img = pic.getPictureData().getData();
                            int width = (int) pic.getCTPicture().getSpPr().getXfrm().getExt().getCx();
                            int height = (int) pic.getCTPicture().getSpPr().getXfrm().getExt().getCy();
                            int pictureType = pic.getPictureData().getPictureType();
                            String fileName = pic.getPictureData().getFileName();
                            InputStream inputStream = new ByteArrayInputStream(img);
                            try {
                                destRun.addPicture(inputStream, pictureType, fileName, width, height);
                            } catch (InvalidFormatException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            } else if (elementType == BodyElementType.TABLE) {
                XWPFTable table = (XWPFTable) bodyElement;
                destDoc.createTable();
                copyStyle(srcDoc, destDoc, srcDoc.getStyles().getStyle(table.getStyleID()));
                int pos = destDoc.getTables().size() - 1;
                destDoc.setTable(pos, table);
            }
        }
    }
    private static void copyStyle(XWPFDocument srcDoc, XWPFDocument destDoc, XWPFStyle style) {
        if (destDoc == null || style == null) {
            return;
        }
        if (destDoc.getStyles() == null) {
            destDoc.createStyles();
        }
        List<XWPFStyle> usedStyleList = srcDoc.getStyles().getUsedStyleList(style);
        for (XWPFStyle xwpfStyle : usedStyleList) {
            destDoc.getStyles().addStyle(xwpfStyle);
        }
    }

    /**
     * 复制文档的 layout
     *
     * @param srcDoc  源 document
     * @param destDoc 目的 document
     */
    public static void copyLayout(XWPFDocument srcDoc, XWPFDocument destDoc) {
        CTPageMar pgMar = srcDoc.getDocument().getBody().getSectPr().getPgMar();
        BigInteger bottom = pgMar.getBottom();
        BigInteger footer = pgMar.getFooter();
        BigInteger gutter = pgMar.getGutter();
        BigInteger header = pgMar.getHeader();
        BigInteger left = pgMar.getLeft();
        BigInteger right = pgMar.getRight();
        BigInteger top = pgMar.getTop();

        CTPageMar addNewPgMar = destDoc.getDocument().getBody().addNewSectPr().addNewPgMar();
        addNewPgMar.setBottom(bottom);
        addNewPgMar.setFooter(footer);
        addNewPgMar.setGutter(gutter);
        addNewPgMar.setHeader(header);
        addNewPgMar.setLeft(left);
        addNewPgMar.setRight(right);
        addNewPgMar.setTop(top);

        CTPageSz pgSzSrc = srcDoc.getDocument().getBody().getSectPr().getPgSz();
        if(pgSzSrc != null) {
            BigInteger code = pgSzSrc.getCode();
            BigInteger h = pgSzSrc.getH();
            STPageOrientation.Enum orient = pgSzSrc.getOrient();
            BigInteger w = pgSzSrc.getW();

            CTPageSz addNewPgSz = destDoc.getDocument().getBody().addNewSectPr().addNewPgSz();
            addNewPgSz.setCode(code);
            addNewPgSz.setH(h);
            addNewPgSz.setOrient(orient);
            addNewPgSz.setW(w);
        }
    }
}
