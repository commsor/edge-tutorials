(ns tutorial.vent.lib
  (:require
    [tutorial.vent.db :as db]))

(defn favorites
  []
  [{:id "1"
    :text "A favorited tweet"
    :author {:name "John smith"}
    :username "john_smith"
    :favorite? true}
   {:id "2"
    :text "Another favorite tweet"
    :author {:name "Jane Smith"}
    :username "jane_smith"
    :favorite? true}])

(defn all
  []
  (let [data (db/read)
        vents (:vents data)
        users (:users data)
        get-author (fn [vent] (get users (get vent :username)))
        add-author-to-vent (fn [vent] (merge vent {:author (get-author vent)}))
        user-annotated-vents (map add-author-to-vent vents)]
    user-annotated-vents))

(defn followers
  [{:keys [username]}]
  {"john_smith"
   {:name "John Smith"}
   "jane_smith"
   {:name "Jane Smith"
    :following? true}})

(defn following
  [{:keys [username]}]
  {"jane_smith"
   {:name "Jane Smith"
    :following? true}})

(defn toggle-favorite
  [{:keys [vent-id]}]
  (println "toggling favorite on" vent-id))

(defn- generate-id
  []
  (str (java.util.UUID/randomUUID)))

(defn add-vent
  [{:keys [text username]}]
  (println username
           "is venting about" text
           "with id" (generate-id)))

(defn toggle-follow
  [{:keys [to-follow username]}]
  (println username "is follwoing or unfollowing" to-follow))
