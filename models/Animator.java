package battleship.models;

/* @author Area 51 Block Party:
 * Christopher Brantley
 * Last Updated 11/26/2019
 * Animator class serves as an accessor for pre made animations.
 */

import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public final class Animator {

    private ImageView imageView;
    private Timeline timeLine;

    //Enumerator -> Animation Type
    public static final int EXPLOSION = -1;
    //Enumerator -> Image file Path
    public static final String IMAGESRCPATH = "src/battleship/assets/images/";

    public Animator (int _type) {
        if (_type == Animator.EXPLOSION) {
            this.createExplosionAnimation();
        }
    }

    // Pieces together the images of an animation at the correct time interval.
    public void createExplosionAnimation() {
        try {
            Image smoke = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke1.png"));
            Image smoke2 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke2.png"));
            Image smoke3 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke3.png"));
            Image smoke4 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke4.png"));
            Image smoke5 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke5.png"));
            Image smoke6 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke6.png"));
            Image smoke7 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke7.png"));
            Image smoke8 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke8.png"));
            Image smoke9 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke9.png"));
            Image smoke10 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke10.png"));
            Image smoke11 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke11.png"));
            Image smoke12 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke12.png"));
            Image smoke13 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke13.png"));
            Image smoke14 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke14.png"));
            Image smoke15 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke15.png"));
            Image smoke16 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke16.png"));
            Image smoke17 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke17.png"));
            Image smoke18 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke18.png"));
            Image smoke19 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke19.png"));
            Image smoke20 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke20.png"));
            Image smoke21 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke21.png"));
            Image smoke22 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke22.png"));
            Image smoke23 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke23.png"));
            Image smoke24 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke24.png"));
            Image smoke25 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke25.png"));
            Image smoke26 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke26.png"));
            Image smoke27 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke27.png"));
            Image smoke28 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke28.png"));
            Image smoke29 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke29.png"));
            Image smoke30 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke30.png"));
            Image smoke31 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke31.png"));
            Image smoke32 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke32.png"));
            Image smoke33 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke33.png"));
            Image smoke34 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke34.png"));
            Image smoke35 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke35.png"));
            Image smoke36 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke36.png"));
            Image smoke37 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke37.png"));
            Image smoke38 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke38.png"));
            Image smoke39 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke39.png"));
            Image smoke40 = new Image(new FileInputStream(Animator.IMAGESRCPATH + "explosion/smoke40.png"));
            ImageView smokeView = new ImageView();
            final Timeline timeline = new Timeline();
            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.millis(0), (ActionEvent t) -> {
                        smokeView.setImage(smoke);
                    }),
                    new KeyFrame(Duration.millis(100), (ActionEvent t) -> {
                        smokeView.setImage(smoke2);
                    }),
                    new KeyFrame(Duration.millis(200), (ActionEvent t) -> {
                        smokeView.setImage(smoke3);
                    }),
                    new KeyFrame(Duration.millis(300), (ActionEvent t) -> {
                        smokeView.setImage(smoke4);
                    }),
                    new KeyFrame(Duration.millis(400), (ActionEvent t) -> {
                        smokeView.setImage(smoke5);
                    }),
                    new KeyFrame(Duration.millis(500), (ActionEvent t) -> {
                        smokeView.setImage(smoke6);
                    }),
                    new KeyFrame(Duration.millis(600), (ActionEvent t) -> {
                        smokeView.setImage(smoke7);
                    }),
                    new KeyFrame(Duration.millis(700), (ActionEvent t) -> {
                        smokeView.setImage(smoke8);
                    }),
                    new KeyFrame(Duration.millis(800), (ActionEvent t) -> {
                        smokeView.setImage(smoke9);
                    }),
                    new KeyFrame(Duration.millis(900), (ActionEvent t) -> {
                        smokeView.setImage(smoke10);
                    }),
                    new KeyFrame(Duration.millis(1000), (ActionEvent t) -> {
                        smokeView.setImage(smoke11);
                    }),
                    new KeyFrame(Duration.millis(1100), (ActionEvent t) -> {
                        smokeView.setImage(smoke12);
                    }),
                    new KeyFrame(Duration.millis(1200), (ActionEvent t) -> {
                        smokeView.setImage(smoke13);
                    }),
                    new KeyFrame(Duration.millis(1300), (ActionEvent t) -> {
                        smokeView.setImage(smoke14);
                    }),
                    new KeyFrame(Duration.millis(1400), (ActionEvent t) -> {
                        smokeView.setImage(smoke15);
                    }),
                    new KeyFrame(Duration.millis(1500), (ActionEvent t) -> {
                        smokeView.setImage(smoke16);
                    }),
                    new KeyFrame(Duration.millis(1600), (ActionEvent t) -> {
                        smokeView.setImage(smoke17);
                    }),
                    new KeyFrame(Duration.millis(1700), (ActionEvent t) -> {
                        smokeView.setImage(smoke18);
                    }),
                    new KeyFrame(Duration.millis(1800), (ActionEvent t) -> {
                        smokeView.setImage(smoke19);
                    }),
                    new KeyFrame(Duration.millis(1900), (ActionEvent t) -> {
                        smokeView.setImage(smoke20);
                    }),
                    new KeyFrame(Duration.millis(2000), (ActionEvent t) -> {
                        smokeView.setImage(smoke21);
                    }),
                    new KeyFrame(Duration.millis(2100), (ActionEvent t) -> {
                        smokeView.setImage(smoke22);
                    }),
                    new KeyFrame(Duration.millis(2200), (ActionEvent t) -> {
                        smokeView.setImage(smoke23);
                    }),
                    new KeyFrame(Duration.millis(2300), (ActionEvent t) -> {
                        smokeView.setImage(smoke24);
                    }),
                    new KeyFrame(Duration.millis(2400), (ActionEvent t) -> {
                        smokeView.setImage(smoke25);
                    }),
                    new KeyFrame(Duration.millis(2500), (ActionEvent t) -> {
                        smokeView.setImage(smoke26);
                    }),
                    new KeyFrame(Duration.millis(2600), (ActionEvent t) -> {
                        smokeView.setImage(smoke27);
                    }),
                    new KeyFrame(Duration.millis(2700), (ActionEvent t) -> {
                        smokeView.setImage(smoke28);
                    }),
                    new KeyFrame(Duration.millis(2800), (ActionEvent t) -> {
                        smokeView.setImage(smoke29);
                    }),
                    new KeyFrame(Duration.millis(2900), (ActionEvent t) -> {
                        smokeView.setImage(smoke30);
                    }),
                    new KeyFrame(Duration.millis(3000), (ActionEvent t) -> {
                        smokeView.setImage(smoke31);
                    }),
                    new KeyFrame(Duration.millis(3100), (ActionEvent t) -> {
                        smokeView.setImage(smoke32);
                    }),
                    new KeyFrame(Duration.millis(3200), (ActionEvent t) -> {
                        smokeView.setImage(smoke33);
                    }),
                    new KeyFrame(Duration.millis(3300), (ActionEvent t) -> {
                        smokeView.setImage(smoke34);
                    }),
                    new KeyFrame(Duration.millis(3400), (ActionEvent t) -> {
                        smokeView.setImage(smoke35);
                    }),
                    new KeyFrame(Duration.millis(3500), (ActionEvent t) -> {
                        smokeView.setImage(smoke36);
                    }),
                    new KeyFrame(Duration.millis(3600), (ActionEvent t) -> {
                        smokeView.setImage(smoke37);
                    }),
                    new KeyFrame(Duration.millis(3700), (ActionEvent t) -> {
                        smokeView.setImage(smoke38);
                    }),
                    new KeyFrame(Duration.millis(3800), (ActionEvent t) -> {
                        smokeView.setImage(smoke39);
                    }),
                    new KeyFrame(Duration.millis(3900), (ActionEvent t) -> {
                        smokeView.setImage(smoke40);
                    })
            );
            //timeline.setCycleCount(Animation.INDEFINITE);
            // Sets this particular animator object to this effect.
            this.imageView = smokeView;
            this.timeLine = timeline;
        } catch (Exception e) {
            Logger.getLogger(Animator.class.getName()).log(Level.SEVERE, null, e);
        }
    }

        public void playAnimation () {
            this.timeLine.playFromStart();
        }

//*****************     GETTERS     *******************

    public ImageView getImageView () {
        return this.imageView;
    }

//*****************     SETTERS     *******************

    public void setImageViewScale (double _scaleX, double _scaleY) {
        this.imageView.setScaleX(_scaleX);
        this.imageView.setScaleY(_scaleY);
    }

    public void setImageViewLayout (double _layoutX, double _layoutY) {
        this.imageView.setLayoutX(_layoutX);
        this.imageView.setLayoutY(_layoutY);
    }

    public void setAnimationLoop (boolean _truthValue) {
        if(_truthValue) {
            this.timeLine.setCycleCount(Animation.INDEFINITE);
        }
        else {
            this.timeLine.setCycleCount(this.timeLine.getKeyFrames().size());
        }
    }

}