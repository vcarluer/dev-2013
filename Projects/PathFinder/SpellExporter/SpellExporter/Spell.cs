using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace SpellExporter
{
    public class Spell
    {
        public string Name { get; set; }
        public string School { get; set; }
        public string Register { get; set; }
        public string Branch { get; set; }
        public string LevelMagician { get; set; }
        public string LevelPriest { get; set; }
        public string LevelPaladin { get; set; }
        public string LevelBard { get; set; }
        public string LevelDruid { get; set; }
        public string LevelStriker { get; set; }

        public string CastingTime { get; set; }
        public string Components { get; set; }
        public string Range { get; set; }
        public string TargetEffectArea { get; set; }
        public string Duration { get; set; }
        public string SavingThrow { get; set; }
        public string SpellResistance { get; set; }
        public string Description { get; set; }
    }
}
