def allStop (): Unit = {
    point = point - point%1
    drawCenteredMessage("score - "+point, red, 40)
    stopAnimation()
}
def movePersScroll (speedX: Double, speedY: Double):Unit = {
    scroll(speedX, speedY) //
    pers.offset(speedX, speedY)
}
def winStop (): Unit = {
    drawCenteredMessage("You win", green, 40)
    stopAnimation()
}

cleari()
setBackground(ColorMaker.rgb(158, 158, 158))

// препятствия
var roads: Seq[Picture] = Seq()
var roads1: Seq[Picture] = Seq()
var Xroad1 = 0
for (i <- 0 to 5) {
    var road1 = Picture.image("road1.png")
    road1.draw()
    road1.setPosition(1000-Xroad1*1050, 200)
    road1.scale(3)
    roads1 = road1 +: roads1
    Xroad1 += 1
}
var roads2: Seq[Picture] = Seq()
var Xroad2 = 0
for (i <- 0 to 2) {
    var road2 = Picture.image("road2.png")
    road2.draw()
    road2.setPosition(1000-Xroad2*1050, -220)
    road2.scale(3)
    roads2 = road2 +: roads2
    Xroad2 += 1
}

var bars: Seq[Picture] = Seq()
var Xbars = 0
for (i <- 0 to 100) {
    var bar = Picture.image("gorh.png")
    bar.draw()
    bar.setPosition(-500-Xbars*300, random(-200, 200))
    bar.scale(3)
    bars = bar +: bars
    Xbars += 1
}

// pers
var pers = Picture.image("pers.png")
pers.draw()
pers.scale(2.6)
var SpeedGo = 10.0
var SpeedBoost1 = 25.0
var SpeedBoost2 = 17.0
var persSpeed = SpeedGo
var canBoost = true
var startBoost = true
var timeLampBoost = 0

// finish
var hard = readInt("Напишите сложность от 1 до 3, а если напишите 0, то будет бесконечный режим.")
while (hard <= -1 || hard >= 4) hard = readInt("Напишите сложность от 1 до 3, а если напишите 0, то будет бесконечный режим.")
if (hard == 0) hard = 1
var finish = Picture.image("finish.png")
finish.draw()
finish.setPosition(-10000*hard, 300)
finish.scale(2)

// перед animate
var point = 0.0
var k = 0
toggleFullScreenCanvas()

animate {
    // передвижение
    if (isKeyPressed(Kc.VK_W) && pers.position.y < 200) movePersScroll(0, persSpeed)
    if (isKeyPressed(Kc.VK_S) && pers.position.y > -200) movePersScroll(0, -persSpeed)
    if (isKeyPressed(Kc.VK_E)) {
        for (road1 <- roads1) {
            for (road2 <- roads2) {
                if (startBoost && (road1.intersects(pers) || road2.intersects(pers))) {
                    persSpeed = SpeedBoost1
                    startBoost = false
                } else {
                    persSpeed = SpeedBoost2
                    startBoost = false
                }
            }
        }
    }
    if (hard != 0) finish.offset(persSpeed, 0)
    for (road1 <- roads1) {
        road1.offset(persSpeed, 0)
        if (road1.position.x > 800) {
            road1.setPosition(-2050, 200)
        }
    }
    for (bar <- bars) {
        bar.offset(persSpeed, 0)
        if (bar.position.x > 1000) {
            bar.erase()
        }
        for (y <- -10 to 100) {
            if (bar.position.y == pers.position.y + y && pers.intersects(bar)) {
                allStop()
            } 
        }
    }
        // canBoost = false
    if (persSpeed > SpeedGo) {
        persSpeed -= 0.01
        startBoost = true
    }
    if (isKeyPressed(Kc.VK_Q)) allStop()
    if (finish.position.x >= pers.position.x) winStop ()
    //if (isKeyPressed(Kc.VK_E)) println(pers.position.y)
    
    // конец кадра
    k += 1
    point += persSpeed / 3
    //println(point-point%1)
}
