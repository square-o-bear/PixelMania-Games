cleari()
toggleFullScreenCanvas()
setBackground(ColorMaker.rgb(0, 150, 0))
var point = 0
var block = Picture.image("block.png")
block.draw()
block.scale(5)
block.setPosition(-100, -100)
var blockScale = true
var blockLevel = 1
var proch = 11
var force = 1
var forceLevel = 1
var button1 = true
var avtoForce = false
var blockBulgg = true
var blocks: Seq[Picture] = Seq(block)

var PixelMania = Picture.image("PixelMania.png")
PixelMania.draw() 
PixelMania.scale(5.5)
PixelMania.setPosition(-170, -600)

var text1 = Picture.image("text1.png")
text1.draw()
text1.scale(3)
text1.setPosition(-700, 130)
var dopFon = Picture.image("dopFon.png")
dopFon.draw()
dopFon.scale(5)
dopFon.setPosition(400, 100)
var pointTextX = 400
var pointTextY = 200
var pointText = Picture.textu(("point = "+point), 40, black)
pointText.draw()
pointText.setPosition(pointTextX, pointTextY)

var k = 0

animate {
    if (isKeyPressed(Kc.VK_SPACE) && button1) {
        proch -= force
        button1 = false
        point += blockLevel
        var dopFon = Picture.image("dopFon.png")
        dopFon.draw()
        dopFon.scale(5)
        dopFon.setPosition(400, 100)
        var pointText = Picture.textu(("point = "+point), 40, black)
        pointText.draw()
        pointText.setPosition(pointTextX, pointTextY)
        blockScale = true
        if (blockScale) {
            for (bl <- blocks) bl.scale(0.8)
            blockScale = false
            for (bl <- blocks) bl.setPosition(-70, -60)
        }
    }
    if (proch <= 0) {
        proch = 11
        point += random(blockLevel, blockLevel*5)
        var dopFon = Picture.image("dopFon.png")
        dopFon.draw()
        dopFon.scale(5)
        dopFon.setPosition(400, 100)
        var pointText = Picture.textu(("point = "+point), 40, black)
        pointText.draw()
        pointText.setPosition(pointTextX, pointTextY)
    }
    else 
    if (k % 200 == 0 && k > 1 && avtoForce) {
        proch -= force/2+1
        point += blockLevel
        var dopFon = Picture.image("dopFon.png")
        dopFon.draw()
        dopFon.scale(5)
        dopFon.setPosition(400, 100)
        var pointTextX = 400
        var pointTextY = 200
        var pointText = Picture.textu(("point = "+point), 40, black)
        pointText.draw()
        pointText.setPosition(pointTextX, pointTextY)
        println("proch - " + proch)
    }
    if (!isKeyPressed(Kc.VK_SPACE)) {
        button1 = true
        if (!blockScale) {
            for (bl <- blocks) bl.setPosition(-100, -100)
            for (bl <- blocks) bl.scale(1.25)
        }
        blockScale = true
        
    }

    if (isKeyPressed(Kc.VK_S) && point >= (forceLevel*forceLevel) && forceLevel < 17) {
        force += 1
        point -= (forceLevel*forceLevel)
        forceLevel += 1
        var dopFon = Picture.image("dopFon.png")
        dopFon.draw()
        dopFon.scale(5)
        dopFon.setPosition(400, 100)
        var pointText = Picture.textu(("point = "+point), 40, black)
        pointText.draw()
        pointText.setPosition(pointTextX, pointTextY)
    }

    if (isKeyPressed(Kc.VK_A) && !avtoForce && point >= 10) {
        avtoForce = true
        point -= 10
        var dopFon = Picture.image("dopFon.png")
        dopFon.draw()
        dopFon.scale(5)
        dopFon.setPosition(400, 100)
        var pointText = Picture.textu(("point = "+point), 40, black)
        pointText.draw()
        pointText.setPosition(pointTextX, pointTextY)
    }
    if (isKeyPressed(Kc.VK_D) && blockBulgg && point >= 10) {
        var block = Picture.image("block1.png")
        block.draw()
        block.scale(5)
        block.setPosition(-100, -100)
        blocks = blocks :+ block
        blockLevel += 1
    }
    k += 1
}