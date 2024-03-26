
package jp.minecraftuser.ecoegg.config;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jp.minecraftuser.ecoegg.EcoEgg;
import org.bukkit.DyeColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;


/**
 * MOB卵ファイルR/Wクラス
 *
 * @author ecolight
 */
public class LoaderMob extends LoaderYaml {
    private EcoEgg plg = null;
    private FileConfiguration list = null;

    public LoaderMob(EcoEgg plg_, UUID uuid_) throws SQLException {
        super(plg_, uuid_);
        this.plg = plg_;
        list = getCnf();
        list.options().copyDefaults(true);
    }

    public void saveGen(String name, String type, String date, String version) {
        list.set("gen_user", name);
        list.set("gen_type", type);
        list.set("gen_date", date);
        list.set("gen_plugin_version", version);
    }

    public boolean getUsed() {
        return list.getBoolean("used");
    }

    public String getGenType() {
        return list.getString("gen_type");
    }

    public void saveUse(String name, String type, String date) {
        list.set("use_user", name);
        list.set("use_type", type);
        list.set("use_date", date);
    }


    public long getDate() {
        return list.getLong("date");
    }

    public void setDate(long date) {
        list.set("date", date);
    }

    public void setUsed(boolean b) {
        list.set("used", b);
    }

    public short getMobType() {
        return (short) list.getInt("mobid");
    }

    public void setMobType(short typeid) {
        list.set("mobid", typeid);

    }

    public String getCustomName() {
        return list.getString("name");
    }

    public void setCustomName(String str) {
        list.set("name", str);

    }

    public double getMaxHealth() {
        return list.getDouble("maxhealth");
    }

    public void setMaxHealth(double num) {
        list.set("maxhealth", num);

    }

    public double getHealth() {
        return list.getDouble("health");
    }

    public void setHealth(double num) {
        list.set("health", num);

    }

    public String getOwner() {
        return list.getString("owner");
    }

    public void setOwner(String str) {
        list.set("owner", str);

    }

    public int getMaxDomestication() {
        return list.getInt("maxdomestication");
    }

    public void setMaxDomestication(int num) {
        list.set("maxdomestication", num);

    }

    public int getDomestication() {
        return list.getInt("domestication");
    }

    public void setDomestication(int num) {
        list.set("domestication", num);

    }

    public int getAge() {
        return list.getInt("age");
    }

    public void setAge(int num) {
        list.set("age", num);

    }

    public DyeColor getSheepColor() {
        return DyeColor.valueOf(list.getString("sheepcolor"));
    }

    public void setSheepColor(DyeColor color) {
        list.set("sheepcolor", color.name());

    }

    public Llama.Color getLlamaColor() {
        return Llama.Color.valueOf(list.getString("color"));
    }

    public void setLlamaColor(Llama.Color color) {
        list.set("color", color.name());

    }

    public int getLlamaStrength() {
        return list.getInt("strength");
    }

    public void setLlamaStrength(int strength) {
        list.set("strength", strength);

    }

    public Horse.Style getStyle() {
        return Horse.Style.valueOf(list.getString("style"));
    }

    public void setStyle(Horse.Style style) {
        list.set("style", style.name());

    }

    public Horse.Color getHorseColor() {
        return Horse.Color.valueOf(list.getString("color"));
    }

    public void setHorseColor(Horse.Color color) {
        list.set("color", color.name());

    }

    public Horse.Variant getHorseVariant() {
        return Horse.Variant.valueOf(list.getString("variant"));
    }

    public void SetHorseVariant(Horse.Variant var) {
        list.set("variant", var.name());

    }

    public double getJumpStrength() {
        return list.getDouble("jumpstrength");
    }

    public void setJumpStrength(double num) {
        list.set("jumpstrength", num);

    }

    public double getSpeed() {
        return list.getDouble("speed");
    }

    public void setSpeed(double num) {
        list.set("speed", num);

    }

    public DyeColor getDyeColor() {
        return DyeColor.valueOf(list.getString("collar"));
    }

    public void setDyeColor(DyeColor color) {
        list.set("collar", color.name());

    }

    public Ocelot.Type getOcelotType() {
        return Ocelot.Type.valueOf(list.getString("cattype"));
    }

    public void setOcelotType(Ocelot.Type cattype) {
        list.set("cattype", cattype.name());

    }

    public boolean getPower() {
        return list.getBoolean("power");
    }

    public void setPower(boolean power) {
        list.set("power", power);

    }

    public Rabbit.Type getRabbitType() {
        return Rabbit.Type.valueOf(list.getString("rabbittype"));
    }

    public void setRabbitType(Rabbit.Type rabbittype) {
        list.set("rabbittype", rabbittype.name());

    }

    //typoしてる
    public boolean getBreed() {
        return list.getBoolean("bleed");
    }

    //typoしてる
    public void setBreed(boolean breed) {
        list.set("bleed", breed);

    }

    public Parrot.Variant getParrotVariant() {
        return Parrot.Variant.valueOf(list.getString("parrotvariant"));
    }


    public void setParrotVariant(Parrot.Variant variant) {
        list.set("parrotvariant", variant.name());

    }

    public TropicalFish.Pattern getTropicalFishPattern() {
        return TropicalFish.Pattern.valueOf(list.getString("tropicalfishpattern"));
    }


    public void setTropicalFishPattern(TropicalFish.Pattern pattern) {
        list.set("tropicalfishpattern", pattern.name());

    }

    public DyeColor getTropicalFishBodyColor() {
        return DyeColor.valueOf(list.getString("tropicalfishbodycolor"));
    }

    public void setTropicalFishBodyColor(DyeColor color) {
        list.set("tropicalfishbodycolor", color.name());

    }

    public DyeColor getTropicalFishPatternColor() {
        return DyeColor.valueOf(list.getString("tropicalfishpatterncolor"));
    }

    public void setTropicalFishPatternColor(DyeColor color) {
        list.set("tropicalfishpatterncolor", color.name());

    }

    public List<Map<?, ?>> getEntityEquipment() {
        return list.getMapList("entityequipment");
    }


    public void setEntityEquipment(List<Map> entityEquipment) {
        list.set("entityequipment", entityEquipment);

    }

    public List<Map<?, ?>> getPotionEffectList() {
        return list.getMapList("potioneffectlist");
    }


    public void savePotionEffectList(List<Map> potionEffectList) {
        list.set("potioneffectlist", potionEffectList);


    }

    public List<Map<?, ?>> getTradeList() {
        return list.getMapList("villagersimpletradelist");
    }

    public void setVillagerTradeList(List<Map> recipes) {
        list.set("villagersimpletradelist", recipes);

    }

    /**
     * @deprecated 1.13以前の村人復元用メソッド 1.14以降は{@link LoaderMob#getVillagerProfession()}を使うこと｡
     */
    @Deprecated
    public String getVillagerCareer() {
        return (list.getString("villagercareer"));
    }

    /**
     * @deprecated 1.13以前の村人復元用メソッド 1.14以降は{@link LoaderMob#getVillagerLevel()}を使うこと｡
     */
    @Deprecated
    public int getVillagerCareerLevel() {
        return list.getInt("villagercareerlevel");
    }

    public Villager.Profession getVillagerProfession() {
        return Villager.Profession.valueOf(list.getString("villagerprofession"));
    }

    public void setVillagerProfession(Villager.Profession profession) {
        list.set("villagerprofession", profession.name());

    }

    public int getVillagerLevel() {
        return list.getInt("villagerlevel");
    }

    public void setVillagerLevel(int villagerlevel) {
        list.set("villagerlevel", villagerlevel);

    }

    public int getVillagerExperience() {
        return list.getInt("villagerexperience");
    }

    public void setVillagerExperience(int villagerexperience) {
        list.set("villagerexperience", villagerexperience);

    }

    public boolean getChild() {
        return list.getBoolean("child");
    }

    public void setChild(boolean child) {
        list.set("child", child);

    }

    public boolean getAngry() {
        return list.getBoolean("angry");
    }


    public void setAngry(boolean angry) {
        list.set("angry", angry);

    }

    public boolean getTamed() {
        return list.getBoolean("tamed");
    }

    public void setTamed(boolean tamed) {
        list.set("tamed", tamed);

    }

    public Panda.Gene getPandaMainGene() {
        return Panda.Gene.valueOf(list.getString("pandamaingene"));
    }

    public void setPandaMainGene(Panda.Gene gene) {
        list.set("pandamaingene", gene.name());

    }

    public Panda.Gene getPandaHiddenGene() {
        return Panda.Gene.valueOf(list.getString("pandahiddengene"));
    }

    public void setPandaHiddenGene(Panda.Gene gene) {
        list.set("pandahiddengene", gene.name());

    }

    public Cat.Type getCatType() {
        return Cat.Type.valueOf(list.getString("cattype"));
    }

    public void setCatType(Cat.Type type) {
        list.set("cattype", type.name());

    }

    public Fox.Type getFoxType() {
        return Fox.Type.valueOf(list.getString("foxtype"));
    }

    public void setFoxType(Fox.Type type) {
        list.set("foxtype", type.name());

    }

    public String getFoxFirstTrustedPlayer() {
        return list.getString("foxfirsttrustedplayer");
    }

    public void setFoxFirstTrustedPlayer(String name) {
        list.set("foxfirsttrustedplayer", name);

    }

    public String getFoxSecondTrustedPlayer() {
        return list.getString("foxsecondtrustedplayer");
    }

    public void setFoxSecondTrustedPlayer(String name) {
        list.set("foxsecondtrustedplayer", name);

    }

    public MushroomCow.Variant getMushroomCowVariant() {
        return MushroomCow.Variant.valueOf(list.getString("mushroomcowvariant"));
    }

    public void setMushroomCowVariant(MushroomCow.Variant variant) {
        list.set("mushroomcowvariant", variant.name());
    }

    public Axolotl.Variant getAxolotlVariant() {
        return Axolotl.Variant.valueOf(list.getString("axolotlvariant"));
    }

    public void setAxolotlVariant(Axolotl.Variant variant) {
        list.set("axolotlvariant", variant.name());
    }

    public Frog.Variant getFrogVariant() {
        return Frog.Variant.valueOf(list.getString("frogvariant"));
    }

    public void setFrogVariant(Frog.Variant variant) {
        list.set("frogvariant", variant.name());
    }

    public String getPluginVersion() {
        return list.getString("gen_plugin_version");
    }

}
