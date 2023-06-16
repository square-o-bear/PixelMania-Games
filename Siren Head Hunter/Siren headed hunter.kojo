def sironRandomPosition (): Unit = { // функция spawn сиреноголового
    siren.setPosition(all(random(0, XYbar)).position.x, all(random(0, XYbar)).position.y)
}
def animateStom (): Unit = { // остановить animate
    drawCenteredMessage("score - "+k/10, red, 30)
    stopMp3
    stopAnimation()
}
def piu (): Unit = {
    if(isKeyPressed(Kc.VK_E)) {
        for (xRadio <- -60 to 60) {
            for (yRadio <- -60 to 60) {
                if (pers.position.x + xRadio == siren.position.x && pers.position.y + yRadio == siren.position.y) {
                    sirenHP -= 10
                }
            }
        }
        for (kan <- allKan) {
            if (kan.intersects(pers)) {
                kan.erase()
                kanPodbor += 1
                println(kanPodbor)
            }
        }
        if (kanPodbor >= kolKanGoAway && car.intersects(pers)) {
                    winDef()
                }
    }
}
def piuSiren (): Unit = {
    for (p <- sirenPiu) {
        p.translate(10, 0)
    }
    if (k % 300 == 0) {
        for (p <- sirenPiu) {
            p.erase
        }
    }
    if (k % 150 == 0) {
        for (i <- 0 until 360 by 60) {
             var p = Picture.image("soundPiu.png")
            p.scale(1)
            p.draw()
            p.rotate(i+ random(-5, 5))
            sirenPiu = sirenPiu :+ p
            p.setPosition(siren.position.x+80, siren.position.y+320)
        }
    }
}
def winDef (): Unit = {
    drawCenteredMessage("YOU WIN", green, 40)
    pers.erase()
    stopAnimation()
}

cleari()
setBackground(ColorMaker.rgb(0, 0, 50))
var Xbar = readInt("длинна карты 30 - мало/ 50 - средни/ 100 - может логать")
var Ybar = Xbar
var XYbar = Xbar*Ybar
var all: Seq[Picture] = Seq()
for (x <- 1 to Xbar) {
    for (y <- 1 to Ybar) {
        var floarPart = Picture.image("world.png")
        floarPart.draw()
        floarPart.setPosition(x*100-200, -y*100+200)
        all = all :+ floarPart
    }
}

// car
var car = Picture.image("car.png")
car.draw()
car.scale(0.55)
car.setPosition(-90, 100)

// pers
var pers = Picture.image("pers1.png")
var persHP = 100
var persSpeedGo = 10
pers.draw()
pers.scale(2.5)
var regenHP = 0
var regenSpeed = 0
var dedPers = Picture.image("pers2.png")

// канистры
var kolKan = 5
var kolKanGoAway = 4
var kanPodbor = 0
var allKan: Seq[Picture] = Seq()
for (kan <- 1 to kolKan) {
    var kan = Picture.image("kan.png")
    allKan = kan +: allKan
    kan.draw()
    kan.setPosition(all(random(0, XYbar)).position.x, all(random(0, XYbar)).position.y)
}

// siren
var siren = Picture.image("siren.png")
var sirenHP = 300
var sirenSpeed = 5
siren.draw()
siren.scale(0.25)
sironRandomPosition()
var sirenPiu: Seq[Picture] = Seq()



var soundPlay = true
var k = 0
var startDo = false

var startText = Picture.text("C - для старта")
startText.draw()
startText.scale(2)

println("C - для старта")
println("W - вверх  S - вниз")
println("A - влево  D - вправо")
println("Q - стоп игра  E - атака/взаимодействие")
println("X - выкл музуку  Z - вкл музыку")

toggleFullScreenCanvas()

animate {
    if (isKeyPressed(Kc.VK_C)) {
        startDo = true
        startText.erase()
    }
    if (startDo) {
    // звуки сирены
    if (k == 0) {
        stopMp3
        playMp3Sound("siren-head-sound1.mp3")
    }
    if (soundPlay) {
        timer(49000) {
            stopMp3
            playMp3Sound("siren-head-sound1.mp3")
        }
    }
    if (isKeyPressed(Kc.VK_X)) {
        soundPlay = false
        stopMp3
    }
    if (isKeyPressed(Kc.VK_Z)) {
        soundPlay = true
        playMp3Sound("siren-head-sound1.mp3")
    }
    // regen
    if (regenHP < 100) regenHP += 1
    
    if (persHP < 100 && regenHP % 20 == 0) {
        persHP += 1
        regenHP = 1
    }
    if (persSpeedGo < 10) regenSpeed += 1
    
    if (persSpeedGo < 10 && regenSpeed % 20 == 0) {
        persSpeedGo += 1
        regenSpeed = 1
    }
    
    // stop animate
    if (isKeyPressed(Kc.VK_Q)) animateStom ()
    // playr move part
    if (isKeyPressed(Kc.VK_A) && pers.position.x > -100.0 && !car.intersects(pers)) {
        scroll(-persSpeedGo, 0) // 
        pers.offset(-persSpeedGo, 0)
    }
    if (isKeyPressed(Kc.VK_D) && pers.position.x < ((Xbar*100)) - 125.0) {
        scroll(persSpeedGo, 0) //
        pers.offset(persSpeedGo, 0)
    }
    if (isKeyPressed(Kc.VK_W) && !car.intersects(pers) && pers.position.y < 190.0) {
        scroll(0, persSpeedGo) // 
        pers.offset(0, persSpeedGo)
    }
    if (isKeyPressed(Kc.VK_S) && pers.position.y > -((Ybar*100)) + 200.0) {
        scroll(0, -persSpeedGo) //
        pers.offset(0, -persSpeedGo)
    }
    
    // siren and pers kill
    piu()
    piuSiren()
    for (i <- -50 to 50)
        if (siren.intersects(pers) && siren.position.y+i == pers.position.y) {
            persHP -= 2
            if (persHP <= 0) {
                dedPers.draw()
                dedPers.scale(2.5)
                dedPers.setPosition(pers.position.x, pers.position.y)
                dedPers.rotate(-90)
                pers.erase()
                animateStom ()
            }
    }
    for (p <- sirenPiu) {
        if(p.intersects(pers) && persSpeedGo > 0) {
            persSpeedGo -= 2
            p.erase()
        }
    }
    
    // consol
    if (isKeyPressed(Kc.VK_P)) {
        println(pers.position.x)
        println(pers.position.y)
    }
    // siren head move part
    if (siren.position.x < pers.position.x && !pers.intersects(siren)) siren.offset(sirenSpeed, 0)
    if (siren.position.x > pers.position.x && !pers.intersects(siren)) siren.offset(-sirenSpeed, 0)
    if (siren.position.y < pers.position.y) siren.offset(0, sirenSpeed)
    if (siren.position.y > pers.position.y && !pers.intersects(siren)) siren.offset(0, -sirenSpeed)
    // siren ded
    if (sirenHP <= 0) {
        sironRandomPosition()
        sirenHP = 100
    }
    k += 1
    }
}
var gameTime = 0
/*
    timer(1000) {
        var timeLabel = Picture.textu("")
        gameTime += 1
        timeLabel.update(gameTime)
        if (gameTime == 60) {
            drawCenteredMessage("Time up! You Win", green, 30)
            stopAnimation()
        }
    }
*/