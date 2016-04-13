;; Widget macro and related helper procedures
(ns vaadin.widget)

(def client-boilerplate
  '(let*
	 ((namespace "com_github_mjvesa_spil_SchemeComponent")
	  (self (js-eval (string-append namespace ".self")))
	  (root-element (js-invoke self "getElement"))
	  (list-to-string (fn [lst]
	    (let ((out (open-output-string)))
		  (write lst out)
		  (get-output-string out))))
	  (call-server (lambda (name paramz)
		       (js-invoke self (symbol->string name)  (list-to-string paramz))))
	  (get-state (fn []
	    (js-ref (js-invoke self "getState") "lst")))
	  (append-to-root (lambda (element)
			    (element-append-child! root-element element))))))
(def client-code '())

(defn handle-widget-section [comp section]
  (let ((param-name (cdadr section))
	(section-code (caddr section)))
    (case (first section)
      ((client)
       (set! client-code (append client-code (rest section))))
      ((server-rpc)
       (.registerServerRpc  comp (symbol->string (caadr section)) (eval `(lambda ,param-name ,section-code))))
      (else
       (display (string-append "Unrecognized section: " (car section) "\n"))))))

(def upload-macros [comp]
  (.setComponentCode comp (.toString
			   '(defmacro client-rpc [params code]
			      `(js-set! self ,(symbol->string (car params))
					 (js-closure
					  (lambda (str) (let ((,@(cdr params) (read (open-input-string str))))
							  ,code)))))))
  (.setComponentCode comp (.toString

			   '(defmacro on-state-change [code]
			      `(js-set! self "onStateChange"
					 (js-closure
					  (lambda () ,code)))))))

(defmacro  widget [widget-definition]
  `(let ((comp (SchemeComponent.)))
     (upload-macros comp) ;; TODO needs to be part of the app init
     (set! client-code client-boilerplate)
     (for-each (lambda (def) (handle-widget-section comp  def)) ',widget-definition)
     (.setComponentCode comp (.toString (list (append '(lambda ()) (list client-code)))))
     comp))

(defmacro defwidget [params . widget-definition]
  `(define-macro ,params
     (list 'widget ,@widget-definition)))

(defn call-client [comp func]
  (.callClient comp (.toString func)))

(defn call-client-rpc [comp func args]
  (.callClient comp (.toString func) args))

(defn set-widget-state [comp state]
  (.setLst (.getState comp) (.toString state)))


