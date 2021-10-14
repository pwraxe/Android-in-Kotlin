(x,y)(0,0)-----------> x in positive
|
|
|
| downside y is positive
V
____________________________________________________________________________________________________________________________________________________________
Rotate
    - val animator = ObjectAnimator.ofFloat(star, View.ROTATION,-360f,0f)
      animator.duration = 3000L
      animator.start()
      
      //if you want to add listener to animation
     animator.addListener(object : AnimatorListenerAdapter(){

            override fun onAnimationStart(animation: Animator?) { super.onAnimationStart(animation)  }
            override fun onAnimationStart(animation: Animator?, isReverse: Boolean) { super.onAnimationStart(animation, isReverse)  }
            override fun onAnimationResume(animation: Animator?) { super.onAnimationResume(animation)  }
            override fun onAnimationPause(animation: Animator?) { super.onAnimationPause(animation)  }
            override fun onAnimationEnd(animation: Animator?) { super.onAnimationEnd(animation)  }
            override fun onAnimationEnd(animation: Animator?, isReverse: Boolean) { super.onAnimationEnd(animation, isReverse)  }
            override fun onAnimationRepeat(animation: Animator?) { super.onAnimationRepeat(animation)  }
            override fun onAnimationCancel(animation: Animator?) { super.onAnimationCancel(animation)  }
    })
_____________________________________________________________________________________________________________________________________________________________

Translate 
        // TRANSLATION_X : move object to right (x-axis)
        // TRANSLATION_Y : move object to downside (y-axis)
     - ObjectAnimator.ofFloat(star,View.TRANSLATION_X,500f).start()
_____________________________________________________________________________________________________________________________________________________________
_____________________________________________________________________________________________________________________________________________________________
_____________________________________________________________________________________________________________________________________________________________
_____________________________________________________________________________________________________________________________________________________________
_____________________________________________________________________________________________________________________________________________________________
_____________________________________________________________________________________________________________________________________________________________
_____________________________________________________________________________________________________________________________________________________________
_____________________________________________________________________________________________________________________________________________________________
 
     
     
 
     
